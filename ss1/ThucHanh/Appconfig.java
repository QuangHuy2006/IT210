package org.example.ThucHanh;

public class Appconfig {
    @Bean
    public VIPNotify vipnotify(){
        return new VIPNotify();
    }

    @Bean
    public NormalNotify normalnotify(){
        return new NormalNotify();
    }

    @Bean
    public AccountManagement acc(){
        return AccountManagement();
    }
}
