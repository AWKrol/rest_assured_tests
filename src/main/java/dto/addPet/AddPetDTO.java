package dto.addPet;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AddPetDTO{
	private List<String> photoUrls;
	private String name;
	private int id;
	private Category category;
	private List<Tags> tags;
	private String status;

}