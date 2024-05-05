package com.infybuzz.report;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class CustomReport {

	public static void main(String[] args) {
		try {
			String filePath = FirstReport.reportInputPath + "Student.jrxml";

			Subject subject1 = new Subject("Java", 80);
			Subject subject2 = new Subject("MySQL", 70);
			Subject subject3 = new Subject("PHP", 50);
			Subject subject4 = new Subject("MongoDB", 40);
			Subject subject5 = new Subject("C++", 60);

			List<Subject> list = new ArrayList<Subject>();
			list.add(subject1);
			list.add(subject2);
			list.add(subject3);
			list.add(subject4);
			list.add(subject5);

			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);

			JRBeanCollectionDataSource chartDataSource = new JRBeanCollectionDataSource(list);

			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("studentName", "John");
			parameters.put("tableData", dataSource);

			JasperReport report = JasperCompileManager.compileReport(filePath);

			JasperPrint print = JasperFillManager.fillReport(report, parameters, chartDataSource);

			JasperExportManager.exportReportToPdfFile(print, FirstReport.reportOutputPath + "student.pdf");

			JasperExportManager.exportReportToHtmlFile(print, FirstReport.reportOutputPath + "student.html");

			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(print));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(
					new FileOutputStream(new File(FirstReport.reportOutputPath + "student.xlsx"))));
			exporter.exportReport();

			System.out.println("Report Created...");

		} catch (Exception e) {
			System.out.println("Exception while creating report");
			e.printStackTrace();
		}
	}

}
