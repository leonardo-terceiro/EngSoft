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

import lombok.extern.slf4j.Slf4j;
import upf.engsoft.notificationmail.entity.SubscriberEntity;

@Slf4j
@Service
public class WriterService {

	/**
	 * 
	 * write the CSV file
	 * 
	 * @param file
	 * @param subscriptions
	 * @throws IOException
	 */
	public void csvWriter(File file, List<SubscriberEntity> subscriptions) throws IOException {
		log.info("csvWriter() - START - writing CSV file with [{}] subscriptions", subscriptions.size());
		
		PrintWriter writer = new PrintWriter(file);
		
		CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(
				"id",
				"Nome",
				"Sobrenome",
				"cpf",
				"email",
				"telefone"
				).withDelimiter(','));
		
		int cont = 1;
		for (SubscriberEntity subEnt : subscriptions) {
			log.info("csvWriter() - [LINE_{}] - writing line with [{}]", cont, subEnt);
			
			List<Object> csvLine = Arrays.asList(
					ObjectUtils.defaultIfNull(subEnt.getId(), null),
					ObjectUtils.defaultIfNull(subEnt.getName(), null),
					ObjectUtils.defaultIfNull(subEnt.getLastName(), null),
					ObjectUtils.defaultIfNull(subEnt.getCpf(), null),
					ObjectUtils.defaultIfNull(subEnt.getEmail(), null),
					ObjectUtils.defaultIfNull(subEnt.getCellphone(), null)
					);
			
			csvPrinter.printRecord(csvLine);
			
			log.info("csvWriter() - [LINE_{}] - result: [{}]", cont, csvLine);
			cont++;
		}
		
		csvPrinter.flush();
		csvPrinter.close();
		writer.close();
		
		log.info("csvWriter() - END - Finished writing [{}] lines", subscriptions.size());
	}
	
}
