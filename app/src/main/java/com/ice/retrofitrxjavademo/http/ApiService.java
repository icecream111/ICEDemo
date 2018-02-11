package com.ice.retrofitrxjavademo.http;


import com.ice.retrofitrxjavademo.model.Login;
import com.ice.retrofitrxjavademo.model.NormanBean;
import com.ice.retrofitrxjavademo.model.Regist;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


public interface ApiService {

    //注册
    @POST("app/sys/consumer/register.html")
    Observable<Regist> Register(
            @Query("account") String phonenum,
            @Query("password") String password,
            @Query("code") String code
    );


    //登录
    //    Observable<Login> login(@FieldMap Map<String, String> map);
    @POST("app/sys/consumer/login.html")
    Observable<Login> login(
            @Query("account") String phonenum,
            @Query("password") String password
    );


    //    //获取验证码
    //    @POST("app_user/getCode.dql")
    //    Observable<Code> getCode(@FieldMap Map<String, String> map);


    //    //首页
    //    @FormUrlEncoded
    //    @POST("app_home/toIndex.dql")
    //    Observable<Index> index(@FieldMap Map<String, String> map);

    /**
     * 替换字段 拼接参数
     *
     * @param owner
     * @param repo
     * @return
     */
    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<NormanBean>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo
    );

    /**
     * 添加字段 拼接参数
     *
     * @param query
     * @param since
     * @return
     */
    @GET("/search/repositories")
    Observable<NormanBean> RepositoriesResponse(
            @Query("q") String query,
            @Query("since") String since
    );


    /**
     * 断点续传下载接口
     * 大文件需要加入这个判断，防止下载过程中写入到内存中
     *
     * @param url
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

}


