package com.SchoolWebSite.Security;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dovbnobrd",
            "api_key", "817538118316658",
            "api_secret", "SiDnW0vp0kQ1zuroCw07Q3REiM8"
        ));
    }
}
