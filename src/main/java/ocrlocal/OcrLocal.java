package ocrlocal;


import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import util.TesseractUtil;

@RestController
@RequestMapping("/ocr")
public class OcrLocal {

	

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object hello(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Content-Type", "charset=utf-8");
        System.out.println("PATH +++++++"+getClass().getClassLoader().getResource("tessdata"));
        return "OCR LOCAL VIVO!!!";
    }
	
	@PostMapping()
	public ResponseEntity<String> traduzir(@RequestParam(name="file") MultipartFile file) throws Exception{
		String ext = FilenameUtils.getExtension(file.getOriginalFilename());
		if (!"png".equals(ext) && !"jpg".equals(ext)) {
			return ResponseEntity.badRequest().build();
		}
		 String ocr = "";
		
		try {
			BufferedImage img = ImageIO.read(file.getInputStream());
	        
	         ocr = TesseractUtil.doOCR(img);
	        
		} catch (IOException e) {
			throw new Exception("Erro ao ler arquivo");
		}
		return ResponseEntity.ok(ocr);						
	}

}