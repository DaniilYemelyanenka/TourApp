package by.sysoev.tourApp.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class PDFService {

    private final TemplateEngine templateEngine;

    public byte[] generateTickets() throws IOException {

        Context context = new Context();
        context.setVariable("username","Клиент");
        context.setVariable("tour","Супер тур");
        context.setVariable("bookingPassengersCount",2);
        context.setVariable("startDate","25/03/2026");
        context.setVariable("endDate","30/03/2026");
        context.setVariable("price",1500.0);

        String html = templateEngine.process("ticket",context);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfRendererBuilder pdfRendererBuilder = new PdfRendererBuilder();
        pdfRendererBuilder.withHtmlContent(html,null);
        pdfRendererBuilder.toStream(out);
        pdfRendererBuilder.run();

        return out.toByteArray();
    }


}
