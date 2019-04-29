package com.shenke.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Path;
import java.util.Hashtable;

public class QRCode {

    //@Test
    public static String getQRCode(HttpServletRequest request, String url) {
        String pyath;
        String text = url;
//        String text = "id:00000001";
        int width = 500;
        int height = 500;
        String format = "jpg";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            long date = System.currentTimeMillis();
            System.out.println(request.getServletContext().getRealPath("images/") + date + ".jpg");
          //  Path file = new File(request.getServletContext().getRealPath("images/") + date + ".jpg").toPath();
//            request.getServletContext().getRealPath("images/");
            Path file = new File("D:/QR/" + date + ".jpg").toPath();//在D盘生成二维码图片
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
            return "/path/" + date + ".jpg";
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
