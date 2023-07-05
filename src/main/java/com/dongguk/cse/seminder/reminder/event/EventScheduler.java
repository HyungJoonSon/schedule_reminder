package com.dongguk.cse.seminder.reminder.event;

import com.dongguk.cse.seminder.exception.ErrorCode;
import com.dongguk.cse.seminder.exception.JsonParsingException;
import com.dongguk.cse.seminder.reminder.util.SNSMessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventScheduler {
    @Value("${phone.number}")
    private String phoneNum;
    @Value("${cool_sms.api_key}")
    private String API_KEY;
    @Value("${cool_sms.api_secret}")
    private String API_SECRET;

    private final SNSMessageUtil util;

    @Scheduled(cron = "0 30 23 * * *")	// 매일 23시 30분
    public void sendMessageByCoolSMS() {
        util.sendMessage(phoneNum, getScheduleInFile());
    }

    private String getScheduleInFile(){
        StringBuilder str = new StringBuilder();

        try {
            // Json 전체 받아오기
            ClassPathResource input = new ClassPathResource("ToDoList.json");

            // Paring 하기
            JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(input.getInputStream(), "UTF-8"));
            JSONArray data = (JSONArray) json.get("data");

            // 현재 시간 받아오기(+9의 이유: UTC TO KST)
            LocalDateTime todayDate = LocalDateTime.now();

            // 최신 업데이트 날짜 및 todoList 받아오기
            String updateDate = json.get("update_date").toString();
            str.append("업데이트 날짜: ").append(json.get("update_date").toString()).append("\n");
            str.append("[내일 정기 일정]").append("\n");

            JSONObject todoListSet = (JSONObject) data.get(todayDate.getDayOfWeek().getValue() % 7);

            String dayName = todoListSet.get("day_name").toString();
            JSONArray todoList = (JSONArray) todoListSet.get("to_do_list");

            for (Object o : todoList) {
                JSONObject todoElement = (JSONObject) o;
                str.append("이름: ").append(todoElement.get("content").toString()).append("\n");
                str.append("위치: ").append(todoElement.get("location").toString()).append("\n");
                str.append("시간: ").append(todoElement.get("time").toString()).append("\n\n");
            }
        } catch (Exception e) {
            throw new JsonParsingException(ErrorCode.JSON_PARSING_ERROR);
        }

        return str.toString();
    }
}
