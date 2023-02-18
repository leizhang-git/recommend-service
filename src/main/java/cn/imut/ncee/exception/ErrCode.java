package cn.imut.ncee.exception;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 21:10
 */
public enum ErrCode {

    /**
     * zookeeper创建节点失败
     */
    SYS_ZOOK_CREATE_ERROR("SYS-00001", "sys.zook.create.error");

    /**
     * 异常编码
     */
    private final String errCode;

    /**
     * 异常注释
     */
    private final String errDesc;

    ErrCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrDesc() {
        return this.errDesc;
    }

}
