package myapplication.liangcang.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class CainiXihuanBean {

    /**
     * meta : {"status":0,"server_time":"2017-07-21 10:20:29","account_id":0,"cost":0.042356014251708984,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":false,"num_items":1,"items":[{"goods_id":"257654","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/257/257654.jpg","goods_name":"小米生态链巴氏牙刷 | 净白牙齿进口刷丝科学布局温柔给力 旅行装四色","owner_id":"0","like_count":"4125","price":"59.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=257654","brand_info":{"brand_id":"786","brand_name":"贝医生","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/786.jpg","brand_desc":"北京青禾小贝科技有限公司，简称小贝科技，成立于2016年，是一家新兴的小米生态链企业，专注于为用户提供高品质的口腔护理类产品和服务。公司CEO章骏，是2008年北京奥运会祥云火炬主创设计师。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"59.00","short_goods_name":"小米生态链巴氏牙..."},{"goods_id":"258588","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258588.jpg","goods_name":"珐琅动物系列戒指 | 日本经典畅销款【黑猫/白兔】","owner_id":"0","like_count":"687","price":"189.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=258588","brand_info":{"brand_id":"869","brand_name":"KAZA","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/869.jpg","brand_desc":"KAZA是日本知名配饰品牌，作品风格百变多样，且从设计做工优良，在日本东京等地区拥有多家体验店，一直以来都享有很高的人气。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"189.00","short_goods_name":"珐琅动物系列戒指..."},{"goods_id":"258587","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258587.jpg?t=1500545757","goods_name":"棉花珍珠招财福猫耳勾耳环 | 萌感十足 轻盈闪亮【黑/白】","owner_id":"0","like_count":"610","price":"209.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=258587","brand_info":{"brand_id":"869","brand_name":"KAZA","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/869.jpg","brand_desc":"KAZA是日本知名配饰品牌，作品风格百变多样，且从设计做工优良，在日本东京等地区拥有多家体验店，一直以来都享有很高的人气。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"209.00","short_goods_name":"棉花珍珠招财福猫..."},{"goods_id":"258586","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258586.jpg","goods_name":"摇曳的氢气球耳饰 | 手工制作 像气球一样轻盈活泼【两色可选】","owner_id":"0","like_count":"660","price":"299.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=258586","brand_info":{"brand_id":"868","brand_name":"西山さつき手作","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/868.jpg","brand_desc":"西山さつき是日本知名手作饰品生活作家，曾为日本Sony音乐节演出人员设计过演出配饰，并多次受到日本时尚杂志的赞许。其作品均为手作，色彩轻快明亮，质量轻盈，佩戴舒适防敏，在日本拥有极高的人气。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"299.00","short_goods_name":"摇曳的氢气球耳饰..."}]}
     */

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-21 10:20:29
         * account_id : 0
         * cost : 0.042356014251708984
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean {
        /**
         * has_more : false
         * num_items : 1
         * items : [{"goods_id":"257654","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/257/257654.jpg","goods_name":"小米生态链巴氏牙刷 | 净白牙齿进口刷丝科学布局温柔给力 旅行装四色","owner_id":"0","like_count":"4125","price":"59.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=257654","brand_info":{"brand_id":"786","brand_name":"贝医生","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/786.jpg","brand_desc":"北京青禾小贝科技有限公司，简称小贝科技，成立于2016年，是一家新兴的小米生态链企业，专注于为用户提供高品质的口腔护理类产品和服务。公司CEO章骏，是2008年北京奥运会祥云火炬主创设计师。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"59.00","short_goods_name":"小米生态链巴氏牙..."},{"goods_id":"258588","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258588.jpg","goods_name":"珐琅动物系列戒指 | 日本经典畅销款【黑猫/白兔】","owner_id":"0","like_count":"687","price":"189.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=258588","brand_info":{"brand_id":"869","brand_name":"KAZA","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/869.jpg","brand_desc":"KAZA是日本知名配饰品牌，作品风格百变多样，且从设计做工优良，在日本东京等地区拥有多家体验店，一直以来都享有很高的人气。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"189.00","short_goods_name":"珐琅动物系列戒指..."},{"goods_id":"258587","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258587.jpg?t=1500545757","goods_name":"棉花珍珠招财福猫耳勾耳环 | 萌感十足 轻盈闪亮【黑/白】","owner_id":"0","like_count":"610","price":"209.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=258587","brand_info":{"brand_id":"869","brand_name":"KAZA","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/869.jpg","brand_desc":"KAZA是日本知名配饰品牌，作品风格百变多样，且从设计做工优良，在日本东京等地区拥有多家体验店，一直以来都享有很高的人气。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"209.00","short_goods_name":"棉花珍珠招财福猫..."},{"goods_id":"258586","goods_image":"http://imgs-qn.iliangcang.com/ware/goods/big/2/258/258586.jpg","goods_name":"摇曳的氢气球耳饰 | 手工制作 像气球一样轻盈活泼【两色可选】","owner_id":"0","like_count":"660","price":"299.00","is_gift":"0","sale_by":"liangcang","comment_count":"0","liked":"0","goods_url":"http://imgs-qn.iliangcang.com/i/goods/?id=258586","brand_info":{"brand_id":"868","brand_name":"西山さつき手作","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/868.jpg","brand_desc":"西山さつき是日本知名手作饰品生活作家，曾为日本Sony音乐节演出人员设计过演出配饰，并多次受到日本时尚杂志的赞许。其作品均为手作，色彩轻快明亮，质量轻盈，佩戴舒适防敏，在日本拥有极高的人气。"},"promotion_imgurl":"","discount_price":"","promotion_note":"","shop_price":"299.00","short_goods_name":"摇曳的氢气球耳饰..."}]
         */

        private boolean has_more;
        private int num_items;
        private List<ItemsBean> items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public int getNum_items() {
            return num_items;
        }

        public void setNum_items(int num_items) {
            this.num_items = num_items;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * goods_id : 257654
             * goods_image : http://imgs-qn.iliangcang.com/ware/goods/big/2/257/257654.jpg
             * goods_name : 小米生态链巴氏牙刷 | 净白牙齿进口刷丝科学布局温柔给力 旅行装四色
             * owner_id : 0
             * like_count : 4125
             * price : 59.00
             * is_gift : 0
             * sale_by : liangcang
             * comment_count : 0
             * liked : 0
             * goods_url : http://imgs-qn.iliangcang.com/i/goods/?id=257654
             * brand_info : {"brand_id":"786","brand_name":"贝医生","brand_logo":"http://imgs-qn.iliangcang.com/ware/brand/786.jpg","brand_desc":"北京青禾小贝科技有限公司，简称小贝科技，成立于2016年，是一家新兴的小米生态链企业，专注于为用户提供高品质的口腔护理类产品和服务。公司CEO章骏，是2008年北京奥运会祥云火炬主创设计师。"}
             * promotion_imgurl :
             * discount_price :
             * promotion_note :
             * shop_price : 59.00
             * short_goods_name : 小米生态链巴氏牙...
             */

            private String goods_id;
            private String goods_image;
            private String goods_name;
            private String owner_id;
            private String like_count;
            private String price;
            private String is_gift;
            private String sale_by;
            private String comment_count;
            private String liked;
            private String goods_url;
            private BrandInfoBean brand_info;
            private String promotion_imgurl;
            private String discount_price;
            private String promotion_note;
            private String shop_price;
            private String short_goods_name;

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(String owner_id) {
                this.owner_id = owner_id;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getIs_gift() {
                return is_gift;
            }

            public void setIs_gift(String is_gift) {
                this.is_gift = is_gift;
            }

            public String getSale_by() {
                return sale_by;
            }

            public void setSale_by(String sale_by) {
                this.sale_by = sale_by;
            }

            public String getComment_count() {
                return comment_count;
            }

            public void setComment_count(String comment_count) {
                this.comment_count = comment_count;
            }

            public String getLiked() {
                return liked;
            }

            public void setLiked(String liked) {
                this.liked = liked;
            }

            public String getGoods_url() {
                return goods_url;
            }

            public void setGoods_url(String goods_url) {
                this.goods_url = goods_url;
            }

            public BrandInfoBean getBrand_info() {
                return brand_info;
            }

            public void setBrand_info(BrandInfoBean brand_info) {
                this.brand_info = brand_info;
            }

            public String getPromotion_imgurl() {
                return promotion_imgurl;
            }

            public void setPromotion_imgurl(String promotion_imgurl) {
                this.promotion_imgurl = promotion_imgurl;
            }

            public String getDiscount_price() {
                return discount_price;
            }

            public void setDiscount_price(String discount_price) {
                this.discount_price = discount_price;
            }

            public String getPromotion_note() {
                return promotion_note;
            }

            public void setPromotion_note(String promotion_note) {
                this.promotion_note = promotion_note;
            }

            public String getShop_price() {
                return shop_price;
            }

            public void setShop_price(String shop_price) {
                this.shop_price = shop_price;
            }

            public String getShort_goods_name() {
                return short_goods_name;
            }

            public void setShort_goods_name(String short_goods_name) {
                this.short_goods_name = short_goods_name;
            }

            public static class BrandInfoBean {
                /**
                 * brand_id : 786
                 * brand_name : 贝医生
                 * brand_logo : http://imgs-qn.iliangcang.com/ware/brand/786.jpg
                 * brand_desc : 北京青禾小贝科技有限公司，简称小贝科技，成立于2016年，是一家新兴的小米生态链企业，专注于为用户提供高品质的口腔护理类产品和服务。公司CEO章骏，是2008年北京奥运会祥云火炬主创设计师。
                 */

                private String brand_id;
                private String brand_name;
                private String brand_logo;
                private String brand_desc;

                public String getBrand_id() {
                    return brand_id;
                }

                public void setBrand_id(String brand_id) {
                    this.brand_id = brand_id;
                }

                public String getBrand_name() {
                    return brand_name;
                }

                public void setBrand_name(String brand_name) {
                    this.brand_name = brand_name;
                }

                public String getBrand_logo() {
                    return brand_logo;
                }

                public void setBrand_logo(String brand_logo) {
                    this.brand_logo = brand_logo;
                }

                public String getBrand_desc() {
                    return brand_desc;
                }

                public void setBrand_desc(String brand_desc) {
                    this.brand_desc = brand_desc;
                }
            }
        }
    }
}
