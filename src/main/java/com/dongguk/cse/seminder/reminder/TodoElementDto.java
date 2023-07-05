package com.dongguk.cse.seminder.reminder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TodoElementDto {
    private String content;
    private String location;
    private String time;
}
