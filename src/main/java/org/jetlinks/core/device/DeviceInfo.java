package org.jetlinks.core.device;

import lombok.*;

import java.io.Serializable;

/**
 * @author zhouhao
 * @since 1.0.0
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInfo implements Serializable {
    private static final long serialVersionUID = -6849794470754667710L;

    /**
     * 设备ID
     */
    private String id;

    /**
     * 产品-型号ID
     */
    private String productId;

    /**
     * 消息协议
     */
    private String protocol;

    /**
     * 元数据
     */
    private String metadata;
}
