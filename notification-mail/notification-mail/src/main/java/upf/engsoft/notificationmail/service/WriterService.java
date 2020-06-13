package upf.engsoft.notificationmail.service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import upf.engsoft.notificationmail.entity.SubscriberEntity;

@Service
public class WriterService {

	public void csvWriter(File file, List<SubscriberEntity> subscriptions) throws IOException {
		
		PrintWriter writer = new PrintWriter(file);
		
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
				"id",
				"Nome",
				"Sobrenome",
				"cpf",
				"email",
				"telefone"
				).withDelimiter(','));
		
		for (SubscriberEntity subEnt : subscriptions) {
		
			List<Object> csvLine = Arrays.asList(
					ObjectUtils.defaultIfNull(subEnt.getId(), null),
					ObjectUtils.defaultIfNull(subEnt.getName(), null),
					ObjectUtils.defaultIfNull(subEnt.getLastName(), null),
					ObjectUtils.defaultIfNull(subEnt.getCpf(), null),
					ObjectUtils.defaultIfNull(subEnt.getEmail(), null),
					ObjectUtils.defaultIfNull(subEnt.getCellphone(), null)
					);
			
			csvPrinter.printRecord(csvLine);
			
		}
		
		csvPrinter.flush();
		csvPrinter.close();
		writer.close();
	}
	
}
