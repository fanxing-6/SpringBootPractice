package com.fx.websocketmodule.domain.entity;

import com.fx.usermanagementmodule.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device
{
   private String userId;

    private String deviceId; //设备ID

    private String deviceName; // 设备名称

}
