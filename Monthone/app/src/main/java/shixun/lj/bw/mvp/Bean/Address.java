package shixun.lj.bw.mvp.Bean;

import java.util.List;

/*Time:2019/3/29
 *Author:刘江
 *Description:
 */public class Address {

    /**
     * result : [{"address":"河南 郑州 莞城","createTime":1553887281000,"id":2114,"phone":"17600321687","realName":"刘江","userId":85,"whetherDefault":1,"zipCode":"100023"},{"address":"河南 郑州 莞城","createTime":1553887284000,"id":2115,"phone":"17600321687","realName":"刘江","userId":85,"whetherDefault":2,"zipCode":"100023"},{"address":"河南 郑州 莞城","createTime":1553887285000,"id":2116,"phone":"17600321687","realName":"刘江","userId":85,"whetherDefault":2,"zipCode":"100023"},{"address":"河南 郑州 莞城","createTime":1553887286000,"id":2117,"phone":"17600321687","realName":"刘江","userId":85,"whetherDefault":2,"zipCode":"100023"},{"address":"河南 郑州 莞城","createTime":1553887287000,"id":2118,"phone":"17600321687","realName":"刘江","userId":85,"whetherDefault":2,"zipCode":"100023"},{"address":"河南 郑州 莞城","createTime":1553887288000,"id":2119,"phone":"17600321687","realName":"刘江","userId":85,"whetherDefault":2,"zipCode":"100023"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * address : 河南 郑州 莞城
         * createTime : 1553887281000
         * id : 2114
         * phone : 17600321687
         * realName : 刘江
         * userId : 85
         * whetherDefault : 1
         * zipCode : 100023
         */

        private String address;
        private long createTime;
        private int id;
        private String phone;
        private String realName;
        private int userId;
        private int whetherDefault;
        private String zipCode;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherDefault() {
            return whetherDefault;
        }

        public void setWhetherDefault(int whetherDefault) {
            this.whetherDefault = whetherDefault;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }
    }
}
