package shixun.lj.bw.mvp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import shixun.lj.bw.R;
import shixun.lj.bw.data.Tou;
import shixun.lj.bw.mvp.Bean.Show;

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Show.ResultBean resultBean;
    Context context;
    List<Tou.ResultBean> list;
    private final int xbanner = 0;
    private final int RXXP_TYPE = 1;
    private final int MLSS_TYPE = 2;
    private final int PZSH_TYPE = 3;


    public Myadapter(Show.ResultBean resultBean, Context context, List<Tou.ResultBean> banner) {
        this.resultBean = resultBean;
        this.context = context;
        this.list = banner;

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return xbanner;
        } else if (position == 1) {
            return RXXP_TYPE;
        } else if (position == 2) {
            return MLSS_TYPE;
        } else if (position == 3) {
            return PZSH_TYPE;
        }
        return position;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        if (i == xbanner) {
            view = LayoutInflater.from(context).inflate(R.layout.xbanner, viewGroup, false);
            return new Xbanner(view);
        } else if (i == RXXP_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.xin, viewGroup, false);
            RxxpHolder rxxpHolder = new RxxpHolder(view);
            return rxxpHolder;

        } else if (i == MLSS_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.moli, viewGroup, false);
            MlssHolder mlssHolder = new MlssHolder(view);
            return mlssHolder;

        } else if (i == PZSH_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.pzsh, viewGroup, false);
            PzshHolder pzshHolder = new PzshHolder(view);
            return pzshHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof Xbanner) {
            Xbanner xbanner = (Xbanner) viewHolder;
            xbanner.xBanner.setData(list, null);
            xbanner.xBanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(context).load(list.get(position).getImageUrl()).into((ImageView) view);
                }
            });

        } else if (viewHolder instanceof RxxpHolder) {

            RxxpHolder rxxpHolder = (RxxpHolder) viewHolder;
            final List<Show.ResultBean.RxxpBean.CommodityListBean> rxxp = resultBean.getRxxp().getCommodityList();
            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rxxpHolder.recyclerView.setLayoutManager(layoutManager);
            Xadapter xadapter = new Xadapter(rxxp, context);
            xadapter.setXonclick(new Xadapter.xonclick() {
                @Override
                public void onclick(int i) {

                        onclick.onclick(i);



                }
            });
            rxxpHolder.recyclerView.setAdapter(xadapter);


        } else if (viewHolder instanceof MlssHolder) {
            MlssHolder mlssHolder = (MlssHolder) viewHolder;
            List<Show.ResultBean.MlssBean.CommodityListBeanXX> mlss = resultBean.getMlss().getCommodityList();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            mlssHolder.recyclerView.setLayoutManager(linearLayoutManager);
            Moliadapter moliadapter = new Moliadapter(mlss, context);
            moliadapter.setMonclick(new Moliadapter.monclick() {
                @Override
                public void mclick(int id) {

                        onclick1.onclick1(id);



                }
            });
            mlssHolder.recyclerView.setAdapter(moliadapter);


        } else if (viewHolder instanceof PzshHolder) {
            PzshHolder pzshHolder = (PzshHolder) viewHolder;
            List<Show.ResultBean.PzshBean.CommodityListBeanX> pzsh = resultBean.getPzsh().getCommodityList();
            pzshHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            Pzshadapter pzshadapter = new Pzshadapter(pzsh, context);
            pzshadapter.setPonclick(new Pzshadapter.ponclick() {
                @Override
                public void ponclick(int id) {

                        onclick2.onclick2(id);

                }
            });
            pzshHolder.recyclerView.setAdapter(pzshadapter);

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    //xbanner
    public class Xbanner extends RecyclerView.ViewHolder {
        private final XBanner xBanner;


        public Xbanner(@NonNull View itemView) {
            super(itemView);
            xBanner = itemView.findViewById(R.id.xbanner);
        }
    }


    //热销新品
    public class RxxpHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;


        public RxxpHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.xrecy);
        }
    }

    //品质生活
    public class PzshHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;


        public PzshHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.precy);
        }
    }

    //魔丽时尚
    public class MlssHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recyclerView;


        public MlssHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.mrecy);
        }
    }

    //热销新品的点击事件
    public interface onclick {
        void onclick(int id);
    }

    onclick onclick;

    public void setOnclick(Myadapter.onclick onclick) {
        this.onclick = onclick;
    }

    //魔丽时尚的点击事件
    public interface onclick1 {
        void onclick1(int id);
    }

    onclick1 onclick1;

    public void setOnclick1(Myadapter.onclick1 onclick1) {
        this.onclick1 = onclick1;
    }

    //品质生活的点击事件
    public interface onclick2 {
        void onclick2(int id);
    }

    onclick2 onclick2;

    public void setOnclick2(Myadapter.onclick2 onclick2) {
        this.onclick2 = onclick2;
    }
}
