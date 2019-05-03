package com.example.lgw.mqtt1.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class BrandTypes {

      private  int ImgId;
      private String Tv_brand;
      private String Tv_brand_types;
      @Generated(hash = 1525514124)
      public BrandTypes(int ImgId, String Tv_brand, String Tv_brand_types) {
          this.ImgId = ImgId;
          this.Tv_brand = Tv_brand;
          this.Tv_brand_types = Tv_brand_types;
      }
      @Generated(hash = 1657263751)
      public BrandTypes() {
      }
      public int getImgId() {
          return this.ImgId;
      }
      public void setImgId(int ImgId) {
          this.ImgId = ImgId;
      }
      public String getTv_brand() {
          return this.Tv_brand;
      }
      public void setTv_brand(String Tv_brand) {
          this.Tv_brand = Tv_brand;
      }
      public String getTv_brand_types() {
          return this.Tv_brand_types;
      }
      public void setTv_brand_types(String Tv_brand_types) {
          this.Tv_brand_types = Tv_brand_types;
      }

   
}
