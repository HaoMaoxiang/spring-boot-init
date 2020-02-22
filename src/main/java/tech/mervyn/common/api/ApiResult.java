package tech.mervyn.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

/**
 * @author HaoMaoxiang@126.com
 * @since 2020/2/19
 */
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class ApiResult {

    private int errNo;

    private String errMsg;

    private Object data;

    private Date time;

    public ApiResult() {
        this(0, "success");
    }

    public ApiResult(int errNo, String errMsg) {
        this(errNo, errMsg, null);
    }

    public ApiResult(Object data) {
        this(0, "success", data);
    }

    public ApiResult(int errNo, String errMsg, Object data) {
        this.errNo = errNo;
        this.errMsg = errMsg;
        this.data = data;
        this.time = Date.from(Instant.now());
    }

}
