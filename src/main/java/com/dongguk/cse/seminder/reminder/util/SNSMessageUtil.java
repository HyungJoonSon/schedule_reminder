package com.dongguk.cse.seminder.reminder.util;


import com.dongguk.cse.seminder.exception.CoolSMSException;
import com.dongguk.cse.seminder.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SNSMessageUtil {
    @Value("${cool_sms.api_key}")
    private String API_KEY;
    @Value("${cool_sms.api_secret}")
    private String API_SECRET;

    public void sendMessage(String phoneNum, String content) throws CoolSMSException {
        Message message = new Message();

        message.setFrom(phoneNum);
        message.setTo(phoneNum);
        message.setText(content);


        SingleMessageSentResponse response = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET, "https://api.coolsms.co.kr")
                .sendOne(new SingleMessageSendingRequest(message));

        if (response != null) {
            log.info("Status: {} - Content: {}", response.getStatusCode(), response.getStatusMessage());
        } else {
            throw new CoolSMSException(ErrorCode.SMS_FAIL);
        }
    }
}
