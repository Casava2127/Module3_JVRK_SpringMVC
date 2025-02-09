package ra.demo.service.imp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.demo.config.CloundiaryConfig;
import ra.demo.service.UploadFileService;

import java.io.IOException;
import java.util.Map;

@Service
public class UploadFileServiceImp implements UploadFileService {
    @Autowired
    private Cloudinary cloudiary;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // Lấy tên file gốc từ MultipartFile (xóa đuôi file nếu cần xử lý thêm)
        String originalFilename = file.getOriginalFilename();
        //batman.jpg
        if (originalFilename != null && originalFilename.contains(".")) {
            //batman
            originalFilename = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        }

        // Thêm tên file vào tham số upload (public_id)
        Map uploadParams = ObjectUtils.asMap(
                "public_id", originalFilename  // Đặt tên file hiển thị đúng tên gốc
        );

        // Upload file lên Cloudinary
        Map uploadResult = cloudiary.uploader().upload(file.getBytes(), uploadParams);
        return uploadResult.get("url").toString(); // Return the URL of the uploaded image
    }
}
