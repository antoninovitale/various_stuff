package customerlistprinter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;

import customerlistprinter.model.Customer;
import customerlistprinter.utils.Utils;

/**
 * We have some customer records in a text file (customers.json) -- one customer
 * per line, JSON-encoded. We want to invite any customer within 100km of our
 * Dublin office (GPS coordinates 53.3381985, -6.2592576) to some food and
 * drinks on us. Write a program that will read the full list of customers and
 * output the names and user ids of matching customers (within 100km), sorted by
 * user id (ascending).
 * 
 * @author a.vitale
 * 
 */
public class CustomerListPrinter {
	public final static String FILE_PATH = "src/customers.json";

	public static void main(String[] args) {
		Gson gson = new Gson();
		List<Customer> customers = new ArrayList<Customer>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
			String line = null;
			while ((line = br.readLine()) != null) {
				Customer customer = gson.fromJson(line, Customer.class);
				if (Utils.isWithinDefaultDistance(
						customer.getLatitudeAsDouble(),
						customer.getLongitudeAsDouble(), Utils.DEFAULT_LAT,
						Utils.DEFAULT_LNG)) {
					customers.add(customer);
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (customers.size() > 0) {
			Collections.sort(customers);
			System.out.println("Party attendees:");
			for (Customer c : customers) {
				System.out.println(String.format("Name: %s, ID: %s",
						c.getName(), c.getUserId()));
			}
		}
	}

}
