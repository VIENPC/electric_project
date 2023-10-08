package com.nhutin.electric_project.ServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nhutin.electric_project.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    ServletContext app;

    public File save(MultipartFile file, String folder) {

        try {
            // Lấy đường dẫn tới thư mục 'src/main/resources/static/images/'
            String appRootPath = ResourceUtils.getURL("classpath:static/assets/images/carousel/").getPath();
            File dir = new File(appRootPath);
            System.out.println(dir.getAbsolutePath());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFileName = file.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String name = UUID.randomUUID().toString() + extension; // Sử dụng UUID ngẫu nhiên làm tên file
            File savedFile = new File(dir, name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());

            return savedFile;
        } catch (IllegalStateException e) {
            // Xử lý IllegalStateException (ví dụ: nếu tệp lớn hơn cho phép)
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            // Xử lý FileNotFoundException (ví dụ: nếu thư mục không tồn tại)
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            // Xử lý các lỗi IO khác
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
