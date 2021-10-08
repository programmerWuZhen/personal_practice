package com.example.illustratedhandbook;

import java.util.List;

public class JsonToBean {
    /**
     * statusCode : 000000
     * desc : 请求成功
     * result : {"petFamilyList":[{"petID":1,"name":"地图鱼","engName":"","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img57131407405131.jpg"},{"petID":2,"name":"宝莲灯鱼","engName":"Cheirodco axclrodi","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img39521405664368.jpg"},{"petID":3,"name":"金鱼","engName":"Carassius auratus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img70851405662947.jpg"},{"petID":4,"name":"青龙鱼","engName":"Green Arowana","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img78031410857838.jpg"},{"petID":5,"name":"龙鱼","engName":"","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img20021405662194.jpg"},{"petID":6,"name":"血红龙","engName":"Scleropages formosus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img14391412757710.jpg"},{"petID":7,"name":"孔雀鱼","engName":"guppy","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img1631405652928.jpg"},{"petID":8,"name":"印尼虎鱼","engName":"","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img88631413019443.jpg"},{"petID":9,"name":"红剑鱼","engName":"Xiphias gladius","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img17171409045135.jpg"},{"petID":10,"name":"霓虹燕子","engName":"Pseudomugil furcatus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img85251410514006.jpg"},{"petID":11,"name":"黑背蝴蝶鱼","engName":"Blackbacked butterflyfish","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img8221408007450.jpg"},{"petID":12,"name":"新大钩扯旗","engName":"hyphessobryon socolifi","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img31321411982452.jpg"},{"petID":13,"name":"叶形鱼","engName":"Monocirrhus Polyacanthus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img35841412846839.jpg"},{"petID":14,"name":"棕色高头翻鳃金鱼","engName":"Brown Oranda with curled Opercula","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img83811413450938.jpg"},{"petID":15,"name":"牛头鲷","engName":"Redhump Geophagus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img31341410515464.jpg"},{"petID":16,"name":"熊猫蝶","engName":"Chaetodon adiergastos","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img60231411984840.jpg"},{"petID":17,"name":"橙棘吊","engName":"Orange－socket Surgeonfish","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img58941407228386.jpg"},{"petID":18,"name":"绣蝴蝶鱼","engName":"Wrought iron butterflyfish","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img90581412069110.jpg"},{"petID":19,"name":"汤臣吊","engName":"Thompson","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img52081411547723.jpg"},{"petID":20,"name":"三角鲷","engName":"The Uaru cichlid","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img4711411032596.jpg"}],"totalCount":251}
     */

    private String statusCode;
    private String desc;
    private ResultBean result;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * petFamilyList : [{"petID":1,"name":"地图鱼","engName":"","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img57131407405131.jpg"},{"petID":2,"name":"宝莲灯鱼","engName":"Cheirodco axclrodi","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img39521405664368.jpg"},{"petID":3,"name":"金鱼","engName":"Carassius auratus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img70851405662947.jpg"},{"petID":4,"name":"青龙鱼","engName":"Green Arowana","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img78031410857838.jpg"},{"petID":5,"name":"龙鱼","engName":"","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img20021405662194.jpg"},{"petID":6,"name":"血红龙","engName":"Scleropages formosus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img14391412757710.jpg"},{"petID":7,"name":"孔雀鱼","engName":"guppy","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img1631405652928.jpg"},{"petID":8,"name":"印尼虎鱼","engName":"","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img88631413019443.jpg"},{"petID":9,"name":"红剑鱼","engName":"Xiphias gladius","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img17171409045135.jpg"},{"petID":10,"name":"霓虹燕子","engName":"Pseudomugil furcatus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img85251410514006.jpg"},{"petID":11,"name":"黑背蝴蝶鱼","engName":"Blackbacked butterflyfish","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img8221408007450.jpg"},{"petID":12,"name":"新大钩扯旗","engName":"hyphessobryon socolifi","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img31321411982452.jpg"},{"petID":13,"name":"叶形鱼","engName":"Monocirrhus Polyacanthus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img35841412846839.jpg"},{"petID":14,"name":"棕色高头翻鳃金鱼","engName":"Brown Oranda with curled Opercula","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img83811413450938.jpg"},{"petID":15,"name":"牛头鲷","engName":"Redhump Geophagus","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img31341410515464.jpg"},{"petID":16,"name":"熊猫蝶","engName":"Chaetodon adiergastos","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img60231411984840.jpg"},{"petID":17,"name":"橙棘吊","engName":"Orange－socket Surgeonfish","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img58941407228386.jpg"},{"petID":18,"name":"绣蝴蝶鱼","engName":"Wrought iron butterflyfish","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img90581412069110.jpg"},{"petID":19,"name":"汤臣吊","engName":"Thompson","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img52081411547723.jpg"},{"petID":20,"name":"三角鲷","engName":"The Uaru cichlid","price":"","coverURL":"http://img.boqiicdn.com/Data/BK/P/img4711411032596.jpg"}]
         * totalCount : 251
         */

        private int totalCount;
        private List<PetFamilyListBean> petFamilyList;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public List<PetFamilyListBean> getPetFamilyList() {
            return petFamilyList;
        }

        public void setPetFamilyList(List<PetFamilyListBean> petFamilyList) {
            this.petFamilyList = petFamilyList;
        }

        public static class PetFamilyListBean {
            /**
             * petID : 1
             * name : 地图鱼
             * engName :
             * price :
             * coverURL : http://img.boqiicdn.com/Data/BK/P/img57131407405131.jpg
             */

            private int petID;
            private String name;
            private String engName;
            private String price;
            private String coverURL;

            public int getPetID() {
                return petID;
            }

            public void setPetID(int petID) {
                this.petID = petID;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEngName() {
                return engName;
            }

            public void setEngName(String engName) {
                this.engName = engName;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCoverURL() {
                return coverURL;
            }

            public void setCoverURL(String coverURL) {
                this.coverURL = coverURL;
            }
        }
    }
}
