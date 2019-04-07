package shixun.lj.bw.mvp.Bean;

import java.util.List;

/*Time:2019/3/26
 *Author:刘江
 *Description:
 */public class Gouwuche {

    /**
     * result : [{"commodityId":20,"commodityName":"环球 冬季休闲加绒保暖棉靴 韩版百搭学生小白鞋女士高帮棉鞋","count":1,"pic":"http://172.17.8.100/images/small/commodity/nx/bx/3/1.jpg","price":78}]
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
         * commodityId : 20
         * commodityName : 环球 冬季休闲加绒保暖棉靴 韩版百搭学生小白鞋女士高帮棉鞋
         * count : 1
         * pic : http://172.17.8.100/images/small/commodity/nx/bx/3/1.jpg
         * price : 78
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
