package little.free.library.controller.model;

import little.free.library.entity.Material;
import lombok.Data;
import lombok.NoArgsConstructor;

//This is a Data Transfer Object used to connect between the user adding input and the application returning results. 

@Data
@NoArgsConstructor
public class LibraryMaterial {
	
	private Long materialId;
	private String materialTitle;
	private String materialGenre;
	private String materialCondition;
	
	public LibraryMaterial(Material material) {
		materialId = material.getMaterialId();
		materialTitle = material.getMaterialTitle();
		materialGenre = material.getMaterialGenre();
		materialCondition = material.getMaterialCondition();
	
	}

	public LibraryMaterial(Long materialId, String materialTitle, String materialGenre, String materialCondition) {
	this.materialId = materialId;
	this.materialTitle = materialTitle;
	this.materialGenre = materialGenre;
	this.materialCondition = materialCondition;
	
	}

	public Material toMaterial() {
		Material material = new Material();
		material.setMaterialId(materialId);
		material.setMaterialTitle(materialTitle);
		material.setMaterialGenre(materialGenre);
		material.setMaterialCondition(materialCondition);
		return material;
	}
}
