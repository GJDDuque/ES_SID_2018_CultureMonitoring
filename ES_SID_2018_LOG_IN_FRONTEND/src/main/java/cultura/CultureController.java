package cultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import cultura.data.Data;
import cultura.utilities.StoredProceduresService;
import culture.cultures.Culture;
import culture.cultures.CultureForm;
import culture.cultures.CultureService;
import culture.cultures.CultureServiceImpl;

@Controller
public class CultureController {

	@Bean
	public CultureService CultureServiceImpl() {
		return new CultureServiceImpl();
	}

	private StoredProceduresService storedProcedureService = new StoredProceduresService();

	@GetMapping("/addCultures/{userEmail}")
	public String AddCulture(Model model, @PathVariable String userEmail) {
		model.addAttribute("userEmail", userEmail);
		model.addAttribute("cultureForm", new CultureForm());
		return "addCultures";
	}

	@PostMapping(value = "/addCultures")
	public String AddCulture(@ModelAttribute("cultureForm") CultureForm cultureForm, BindingResult bindingResult,
			Model model) {
		Culture culture = new Culture(cultureForm.getName(), cultureForm.getCulture_responsible(),
				cultureForm.getCulture_description());
		CultureServiceImpl().saveCulture(culture);
		int culture_id = new Data("select MAX(culture_id) from cultures").loadCulturesID();
		if (cultureForm.getTemperature_lower_limit() != null || cultureForm.getTemperature_upper_limit() != null) {
			storedProcedureService.Configure("anyQuery");
			storedProcedureService.SetQuery("insert into measured_variables values ( null, 1 ," + culture_id + ","
					+ cultureForm.getTemperature_lower_limit() + "," + cultureForm.getTemperature_upper_limit());
			storedProcedureService.VoidExecute();
		}
		if (cultureForm.getLight_lower_limit() != null || cultureForm.getLight_upper_limit() != null) {
			storedProcedureService.Configure("anyQuery");
			storedProcedureService.SetQuery("insert into measured_variables values ( null, 2 ," + culture_id + ","
					+ cultureForm.getLight_lower_limit() + "," + cultureForm.getLight_upper_limit());
			storedProcedureService.VoidExecute();
		}
		return "redirect:/addCultures";

	}

}
