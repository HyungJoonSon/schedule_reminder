package com.dongguk.cse.seminder.reminder.util;


import com.dongguk.cse.seminder.exception.CoolSMSException;
import com.dongguk.cse.seminder.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Slf4j
public class SNSMessageUtil {
    private final DefaultMessageService messageService;

    public SNSMessageUtil(String API_KEY, String API_SECRET) {
        this.messageService = NurigoApp.INSTANCE.initialize(API_KEY, API_SECRET, "https://api.coolsms.co.kr");
    }

    public void sendMessage(String phoneNum, String content) throws CoolSMSException {
        Message message = new Message();

        message.setFrom(phoneNum);
        message.setTo(phoneNum);
        message.setText(content);

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));
        if (response != null) {
            log.info("Status: {} - Content: {}", response.getStatusCode(), response.getStatusMessage());
        } else {
            throw new CoolSMSException(ErrorCode.SMS_FAIL, "메세지 전송 실패");
        }
    }
}
