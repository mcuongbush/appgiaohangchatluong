package com.example.giaohangchatluong.api;

import com.example.giaohangchatluong.Model.CTHD;
import com.example.giaohangchatluong.Model.CTVanChuyen;
import com.example.giaohangchatluong.Model.HoaDon;
import com.example.giaohangchatluong.Model.KhachHang;
import com.example.giaohangchatluong.Model.KhachNhan;
import com.example.giaohangchatluong.Model.LoaiHH;
import com.example.giaohangchatluong.Model.LoaiVanChuyen;
import com.example.giaohangchatluong.Model.PhieuGuiHang;
import com.example.giaohangchatluong.Model.PhieuYeuCau;
import com.example.giaohangchatluong.Model.TaiKhoanKH;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface APIService {

    Gson gson = new GsonBuilder().setLenient().setDateFormat("dd/MM/yyyy/ HH:mm:ss").create();
    APIService API_SERVICE = new Retrofit.Builder()
            .baseUrl("https://giaohangchatluong.herokuapp.com/api/ ").addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(APIService.class);
    @GET("khachhang/{sdt}")
    Call<List<KhachHang>> getKhachHang(@Path("sdt") String sdt);

    @GET("ttkhachhang/{makh}")
    Call<List<KhachHang>> getTtKhachHang(@Path("makh") String makh);



    @GET("ctvanchuyen/{mahd}")
    Call<List<CTVanChuyen>> getCTVanChuyen(@Path("mahd") String mahd);

    @GET("hoadon/{sdt}")
    Call<List<HoaDon>> getHoaDon(@Path("sdt") String sdt );

    @GET("khachnhan/{sdt}")
    Call<List<KhachNhan>> getKhachNhan(@Path("sdt") String sdt);

    @GET("khachhang")
    Call<List<KhachHang>> getListKhachHang();

    @GET("loaivc")
    Call<List<LoaiVanChuyen>> getLoaiVC();

    @GET("taikhoan/{TenTK}/{MatKhau}")
    Call<List<TaiKhoanKH>> getTaiKhoanKH(@Path("TenTK") String TenTK, @Path("MatKhau") String MatKhau);

    @GET("cthoadon/{SoHD}")
    Call<List<CTHD>> getCTHD(@Path("SoHD") String SoHD);


    @GET("khoangcach/{addFrom}/{addTo}")
    Call<Integer> getKhoangCach(@Path("addFrom") String addFrom , @Path("addTo") String addTo);



    @PUT ("khachnhan")
    Call<List<KhachNhan>> addKhachNhan(@Body KhachNhan khachNhan);

    @PUT("khachhang")
    Call<List<KhachHang>> addKhachHang(@Body KhachHang khachHang);

    @POST("khachhang")
    Call<KhachHang> updKhachHang(@Body KhachHang khachHang);



    @PUT("taikhoan")
    Call<List<TaiKhoanKH>> addTaiKhoanKH(@Body TaiKhoanKH taiKhoanKH);

    @PUT("phieuguihang")
    Call<List<PhieuGuiHang>> addPhieuGuiHang(@Body PhieuGuiHang phieuGuiHang);

    @PUT("phieuyeucau")
    Call<List<PhieuYeuCau>> addPhieuYeuCau(@Body PhieuYeuCau phieuYeuCau);
}
