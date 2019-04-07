package shixun.lj.bw.mvp.Bean;

import java.util.List;

/*Time:2019/3/28
 *Author:刘江
 *Description:
 */public class Quanzi {

    /**
     * result : [{"commodityId":1,"content":"emmmmmmmmm","createTime":1553736301000,"greatNum":62,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-28/20190328194514.jpg","id":791,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-27/5781020190327202501.jpg","nickName":"iQ_fkP9o","userId":8,"whetherGreat":2},{"commodityId":1,"content":"元气满满","createTime":1553266525000,"greatNum":90,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-26/20190326142936.png","id":790,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-22/6731420190322095525.png","nickName":"2A_dE4I6","userId":752,"whetherGreat":2},{"commodityId":1,"content":"wkq","createTime":1553218505000,"greatNum":2794,"headPic":"http://172.17.8.100/images/small/default/user.jpg","id":789,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-21/6128320190321203505.png","nickName":"HA_DM5T9","userId":11,"whetherGreat":2},{"commodityId":1,"content":"元气满满","createTime":1553216151000,"greatNum":213,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-26/20190326142936.png","id":788,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-21/2380920190321195551.png","nickName":"2A_dE4I6","userId":752,"whetherGreat":2},{"commodityId":1,"content":"????","createTime":1552607214000,"greatNum":3,"headPic":"http://172.17.8.100/images/small/head_pic/2019-03-28/20190328094857.png","id":787,"image":"http://172.17.8.100/images/small/circle_pic/2019-03-14/2346920190314184654.jpg","nickName":"hehe","userId":1636,"whetherGreat":2}]
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
         * commodityId : 1
         * content : emmmmmmmmm
         * createTime : 1553736301000
         * greatNum : 62
         * headPic : http://172.17.8.100/images/small/head_pic/2019-03-28/20190328194514.jpg
         * id : 791
         * image : http://172.17.8.100/images/small/circle_pic/2019-03-27/5781020190327202501.jpg
         * nickName : iQ_fkP9o
         * userId : 8
         * whetherGreat : 2
         */

        private int commodityId;
        private String content;
        private long createTime;
        private int greatNum;
        private String headPic;
        private int id;
        private String image;
        private String nickName;
        private int userId;
        private int whetherGreat;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getGreatNum() {
            return greatNum;
        }

        public void setGreatNum(int greatNum) {
            this.greatNum = greatNum;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }
    }
}
