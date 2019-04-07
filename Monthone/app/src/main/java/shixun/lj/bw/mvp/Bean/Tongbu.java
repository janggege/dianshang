package shixun.lj.bw.mvp.Bean;

/*Time:2019/3/27
 *Author:刘江
 *Description:
 */public class Tongbu {

    /**
     * commodityId : 5
     * count : 3
     */

    private int commodityId;
    private int count;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Tongbu(int commodityId, int count) {
        this.commodityId = commodityId;
        this.count = count;
    }



}
