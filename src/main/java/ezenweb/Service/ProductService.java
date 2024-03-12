package ezenweb.Service;

import ezenweb.medel.dao.ProductDao;
import ezenweb.medel.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private FileService fileService;

    // # 1. 등록 서비스/처리 요청
    public boolean postProductRegister( ProductDto productDto ){
        List< String > list = new ArrayList<>();
        System.out.println("ProductService.postProductRegister");
        
        // 첨부파일 업로드 처리
        productDto.getUploadFiles().forEach((uploadFile) ->{
            String fileName = fileService.fileUpload(uploadFile);
            if(fileName != null) list.add(fileName);
        });
        productDto.setPimg(list);
        
        return productDao.postProductRegister(productDto);
    }


    // # 2. 제품 출력 (  지도에 출력할 ) 요청
    public List<ProductDto> getProductList(){
        System.out.println("ProductService.getProductList");
        return productDao.getProductList();
    }




}
