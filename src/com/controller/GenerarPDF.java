package com.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.model.funciones.Informe;
import com.model.fases.FaseFolicular;
import com.model.fases.FaseLutea;
import com.model.fases.FaseMenstrual;
import com.model.fases.FaseOvulacion;
import com.controller.GenerateDiaFases;
import com.model.funciones.Menstruacion;
import com.model.usuario.Usuario;

/**
 * La clase GenerarPDF se encarga de generar un informe en formato PDF.
 * El informe incluye información sobre el ciclo menstrual de la usuaria.
 */
public class GenerarPDF {
    private FaseMenstrual faseMenstrual;
    private FaseFolicular faseFolicular;
    private FaseOvulacion faseOvulacion;
    private FaseLutea faseLutea;
    private GenerateDiaFases generateDiaFases;
    private Menstruacion menstruacion;

    /**
     * Este método genera un informe en formato PDF.
     * El informe incluye información como el nombre de la usuaria, la fecha de nacimiento,
     * la última menstruación, la media duración del periodo y del ciclo, entre otros.
     * El nombre del archivo PDF será "Informe_mesActual_añoActual.pdf".
     */
    public static void generarInforme(Menstruacion menstruacion, Usuario usuario) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter monthYearFormat = DateTimeFormatter.ofPattern("MM_yyyy");
        LocalDate fechaInforme = LocalDate.now();
        String fileName = "Informe_" + fechaInforme.format(monthYearFormat) + ".pdf";

        //Insertar valores en informe
        Informe informe = new Informe();
        informe.setUsuario(usuario.getUsuario());
        informe.setEdad(usuario.getEdad());
        informe.setLastperiod(menstruacion.getLastperiod());
        informe.setMediaSangrado(menstruacion.getMediaSangrado());
        informe.setMediaCiclo(menstruacion.getMediaCiclo());

        String usuario_default = "prueba";
        if (informe.getUsuario() != null) {
            usuario_default = informe.getUsuario();
        }
        String lastPeriod = "N/A"; // Default value
        if (informe.getLastperiod() != null) {
            lastPeriod = sdf.format(informe.getLastperiod());
        }

        String inicioFaseMenstrual = "N/A"; // Valor por defecto
        if (informe.getInicioFaseMenstrual() != null) {
            inicioFaseMenstrual = sdf.format(informe.getInicioFaseMenstrual());
        }

        String inicioFaseFolicular = "N/A"; // Valor por defecto
        if (informe.getInicioFaseFolicular() != null) {
            inicioFaseFolicular = sdf.format(informe.getInicioFaseFolicular());
        }

        String inicioFaseOvulacion = "N/A"; // Valor por defecto
        if (informe.getInicioFaseOvulacion() != null) {
            inicioFaseOvulacion = sdf.format(informe.getInicioFaseOvulacion());
        }

        String inicioFaseLutea = "N/A"; // Valor por defecto
        if (informe.getInicioFaseLutea() != null) {
            inicioFaseLutea = sdf.format(informe.getInicioFaseLutea());
        }
        String siguientePeriodo = "N/A"; // Valor por defecto
        if (informe.getInicioSiguientePeriodo() != null) {
            siguientePeriodo = sdf.format(informe.getInicioSiguientePeriodo());
        }
        // Imprimir valores para depuración
        System.out.println("Nombre: " + informe.getNombre());
        System.out.println("Edad: " + informe.getEdad());
        System.out.println("Última Menstruación: " + lastPeriod);
        System.out.println("Media Duración del Periodo: " + informe.getMediaSangrado());
        System.out.println("Media Duración del Ciclo: " + informe.getMediaCiclo());

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            System.out.println("Documento PDF abierto correctamente.");

            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            System.out.println("Generando informe...");
            document.add(new Paragraph("Nombre: " + usuario_default, boldFont));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Edad: " + informe.getEdad(), boldFont));
            document.add(new Paragraph("\nInformaciones generales:", boldFont));
            document.add(new Paragraph("Última Menstruación: " + lastPeriod));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Media Duración del Periodo: " + informe.getMediaSangrado()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Media Duración del Ciclo: " + informe.getMediaCiclo()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Menstruación: " + informe.getDuracionFaseMenstrual()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Folicular: " + informe.getDuracionFaseFolicular()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Ovulación: " + informe.getDuracionFaseOvulacion()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Duración Fase Lútea: " + informe.getDuracionFaseLutea()));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Menstrual: " + inicioFaseMenstrual));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Folicular: " + inicioFaseFolicular));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Ovulación: " + inicioFaseOvulacion));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Fase Lútea: " + inicioFaseLutea));
            document.add(new Paragraph("\nPrevisión siguiente mes:", boldFont));
            System.out.println("Generando informe...");
            document.add(new Paragraph("Inicio Siguiente Periodo: " + siguientePeriodo));
            document.add(new Paragraph("\nFecha del informe: " + fechaInforme.format(dtf)));
            System.out.println("Generando informe...");
            document.add(new Paragraph("\"Los juegos de Sangre\"", boldFont));
            System.out.println("Generando informe...");

            document.close();
            System.out.println("Documento PDF cerrado correctamente.");
        } catch (DocumentException e) {
            e.printStackTrace();
            System.out.println("Error al generar el documento PDF: " + e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al encontrar el archivo para el documento PDF: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }
}
