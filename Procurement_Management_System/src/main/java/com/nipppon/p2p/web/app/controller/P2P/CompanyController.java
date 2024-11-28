package com.nipppon.p2p.web.app.controller.P2P;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nipppon.p2p.web.app.dto.P2P.Masters.CategoryDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.DepartmentHeadDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.DivisionDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.HSN_SACDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.ProductDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.PurchaseTypeDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.SubCategoryDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.UOMDTO;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Category;
import com.nipppon.p2p.web.app.entity.P2P.Masters.DepartmentHead;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Division;
import com.nipppon.p2p.web.app.entity.P2P.Masters.HSN_SAC;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Product;
import com.nipppon.p2p.web.app.entity.P2P.Masters.SubCategory;
import com.nipppon.p2p.web.app.entity.P2P.Masters.UOM;
import com.nipppon.p2p.web.app.service.P2P.CompanyService;

@RestController
@RequestMapping("/api/p2p/company")
public class CompanyController{
	@Autowired
	private CompanyService companyService;

	@PostMapping("/addDiv")
	public ResponseEntity<?> createDivision(@RequestBody DivisionDTO divisionDTO){
		Optional<Division> existingDivision = companyService.findByDivisionNameAndDeleteStatus(divisionDTO.getDivisionName(),0);
		if(existingDivision.isPresent()){
			return new ResponseEntity<>("Division already exists", HttpStatus.CONFLICT);
	    }
		Division savedDivision = companyService.saveDivision(divisionDTO);
        return new ResponseEntity<>(savedDivision, HttpStatus.CREATED);
    }

	@GetMapping("/divisions")
	public ResponseEntity<?> getDivisionsWithCreatorDetails(){
		List<DivisionDTO> listOfDivisions = companyService.getDivisionsWithCreatorDetails();
		return new ResponseEntity<>(listOfDivisions, HttpStatus.OK);
	}

	@PutMapping("/deleteDiv/{id}")
	public ResponseEntity<?> deleteDivision(@PathVariable Long id){
		companyService.deleteDivision(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getDivs")
	public ResponseEntity<?> getAllDivisions(){
		List<DivisionDTO> listOfDivisions = companyService.getAllDivisions();
		return new ResponseEntity<>(listOfDivisions, HttpStatus.OK);
	}

	@PostMapping("/addCat")
	public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
		Optional<Category> existingCategory = companyService.findByCategoryNameAndDeleteStatus(categoryDTO.getCategoryName(),0);
		if(existingCategory.isPresent()){
			return new ResponseEntity<>("Category already exists", HttpStatus.CONFLICT);
		}
		Category savedCategory = companyService.saveCategory(categoryDTO);
		return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
	}

	@GetMapping("/cats")
	public ResponseEntity<?> getCategoriesWithCreatorDetails(){
		List<CategoryDTO> listOfCategories = companyService.getCategoriesWithCreatorDetails();
		return new ResponseEntity<>(listOfCategories, HttpStatus.OK);
	}

	@PutMapping("/deleteCat/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long id){
		companyService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getCats")
	public ResponseEntity<?> getAllCategories(){
		List<CategoryDTO> listOfCategories = companyService.getAllCategories();
		return new ResponseEntity<>(listOfCategories, HttpStatus.OK);
	}

	@GetMapping("/getCatsOnPurType/{purType}")
	public ResponseEntity<?> getAllCategoriesByPurchaseType(@PathVariable String purType){
		List<CategoryDTO> listOfCategories = companyService.getAllCategoriesByPurchaseType(purType);
		return new ResponseEntity<>(listOfCategories, HttpStatus.OK);
	}

	@PostMapping("/addSubCat")
	public ResponseEntity<?> createSubCategory(@RequestBody SubCategoryDTO subCategoryDTO){
		Optional<SubCategory> existingSubCategory = companyService.findBySubCategoryNameAndCatIdAndDeleteStatus(subCategoryDTO.getSubCategoryName(),subCategoryDTO.getCategoryId(),0);
		if(existingSubCategory.isPresent()){
			return new ResponseEntity<>("Sub-Category already exists", HttpStatus.CONFLICT);
		}
		SubCategory savedSubCategory = companyService.saveSubCategory(subCategoryDTO);
		return new ResponseEntity<>(savedSubCategory, HttpStatus.CREATED);
	}

	@GetMapping("/subCats")
	public ResponseEntity<?> getSubCategoriesWithCreatorDetails(){
		List<SubCategoryDTO> listOfSubCategories = companyService.getSubCategoriesWithCreatorDetails();
		return new ResponseEntity<>(listOfSubCategories, HttpStatus.OK);
	}

	@PutMapping("/deleteSubCat/{id}")
	public ResponseEntity<?> deleteSubCategory(@PathVariable Long id){
		companyService.deleteSubCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getSubCats/{id}")
	public ResponseEntity<?> getAllSubCategoriesById(@PathVariable Long id){
		List<SubCategoryDTO> listOfSubCategories = companyService.getAllSubCategoriesById(id, 0);
		return new ResponseEntity<>(listOfSubCategories, HttpStatus.OK);
	}

	@PostMapping("/addUom")
	public ResponseEntity<?> createUOM(@RequestBody UOMDTO uomDTO){
		Optional<UOM> existingUom = companyService.findByUomNameAndDeleteStatus(uomDTO.getUomName(),0);
		if(existingUom.isPresent()){
			return new ResponseEntity<>("UOM already exists", HttpStatus.CONFLICT);
	    }
		UOM savedUom = companyService.saveUOM(uomDTO);
        return new ResponseEntity<>(savedUom, HttpStatus.CREATED);
    }

	@GetMapping("/uoms")
	public ResponseEntity<?> getUOMWithCreatorDetails(){
		List<UOMDTO> listOfUom = companyService.getUOMWithCreatorDetails();
		return new ResponseEntity<>(listOfUom, HttpStatus.OK);
	}

	@PutMapping("/deleteUom/{id}")
	public ResponseEntity<?> deleteUOM(@PathVariable Long id){
		companyService.deleteUOM(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getUoms")
	public ResponseEntity<?> getAllUOMs(){
		List<UOMDTO> listOfUoms = companyService.getAllUOMs();
		return new ResponseEntity<>(listOfUoms, HttpStatus.OK);
	}	

	@PostMapping("/addHsn")
	public ResponseEntity<?> createHSN(@RequestBody HSN_SACDTO hsn_SACDTO){
		Optional<HSN_SAC> existingHsn = companyService.findByHsnCodeAndDeleteStatus(hsn_SACDTO.getHsnCode(),0);
		if(existingHsn.isPresent()){
			return new ResponseEntity<>("HSN/SAC Code already exists", HttpStatus.CONFLICT);
	    }
		HSN_SAC savedHsn = companyService.saveHSN(hsn_SACDTO);
        return new ResponseEntity<>(savedHsn, HttpStatus.CREATED);
    }

	@GetMapping("/hsns")
	public ResponseEntity<?> getHSNWithCreatorDetails(){
		List<HSN_SACDTO> listOfHsn = companyService.getHSNWithCreatorDetails();
		return new ResponseEntity<>(listOfHsn, HttpStatus.OK);
	}

	@PutMapping("/deleteHsn/{id}")
	public ResponseEntity<?> deleteHSN(@PathVariable Long id){
		companyService.deleteHSN(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/getHsns")
	public ResponseEntity<?> getAllHSNs(){
		List<HSN_SACDTO> listOfHsns = companyService.getAllHSNs();
		return new ResponseEntity<>(listOfHsns, HttpStatus.OK);
	}
	
	@GetMapping("/getPurTypes")
	public ResponseEntity<?> getAllPurTypes(){
		List<PurchaseTypeDTO> listOfPts = companyService.getAllPurTypes();
		return new ResponseEntity<>(listOfPts, HttpStatus.OK);
	}

	@GetMapping("/getProductCode/{divId}")
	public ResponseEntity<?> getProductCode(@PathVariable Long divId) {
		String productCode = companyService.getProductCode(divId);
		return new ResponseEntity<>(productCode, HttpStatus.OK);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){
		Optional<Product> existingProduct = companyService.findByProductDetailsAndDeleteStatus(productDTO.getProductName(), productDTO.getProdDivId(), productDTO.getPurchaseType(), productDTO.getCategoryId(), productDTO.getSubCategoryId(), 0);
		if(existingProduct.isPresent()){
			return new ResponseEntity<>("Product already exists", HttpStatus.CONFLICT);
	    }
		Product savedProduct = companyService.saveProduct(productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

	@GetMapping("/products")
	public ResponseEntity<?> getProductsWithCreatorDetails(){
		List<ProductDTO> listOfProds = companyService.getProductsWithCreatorDetails();
		return new ResponseEntity<>(listOfProds, HttpStatus.OK);
	}

	@PutMapping("/deleteProduct/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
		companyService.deleteProduct(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/updateProducts/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		try {
			companyService.updateProduct(id, productDTO);
			return ResponseEntity.ok("Product Details Updated successfully.");
		}catch(UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Product Details.");
		}
	}

	@GetMapping("/getProduct/{id}")
	public ResponseEntity<?> getProductDetails(@PathVariable Long id){
		Product product = companyService.getProductDetails(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/viewProduct/{id}")
	public ResponseEntity<?> viewProductDetails(@PathVariable Long id){
		ProductDTO productDTO = companyService.viewProductDetails(id);
		return new ResponseEntity<>(productDTO, HttpStatus.OK);
	}

	@GetMapping("/getProducts/{divId}/{purType}")
	public ResponseEntity<?> getProducts(@PathVariable Long divId, @PathVariable String purType){
		List<ProductDTO> listOfProds = companyService.getProducts(divId, purType);
		return new ResponseEntity<>(listOfProds, HttpStatus.OK);
	}

	@PostMapping("/addDeptHead")
	public ResponseEntity<?> createDeptHead(@RequestBody DepartmentHeadDTO departmentHeadDTO){
		Optional<DepartmentHead> existingDeptHead = companyService.findByDeptHeadAndDeleteStatus(departmentHeadDTO.getBranchId(), departmentHeadDTO.getDeptId(), departmentHeadDTO.getDeptHeadId(), 0);
		if(existingDeptHead.isPresent()){
			return new ResponseEntity<>("Dept. Head already exists", HttpStatus.CONFLICT);
		}
		DepartmentHead saveDeptHead = companyService.saveDeptHead(departmentHeadDTO);
		return new ResponseEntity<>(saveDeptHead, HttpStatus.CREATED);
	}

	@GetMapping("/deptHeads/{bId}")
	public ResponseEntity<?> getDeptHeadsWithCreatorDetails(@PathVariable int bId){
		List<DepartmentHeadDTO> listOfDepts = companyService.getDeptHeadsWithCreatorDetails(bId);
		return new ResponseEntity<>(listOfDepts, HttpStatus.OK);
	}

	@PutMapping("/deleteDeptHead/{id}")
	public ResponseEntity<?> deleteDeptHead(@PathVariable Long id) {
		companyService.deleteDeptHead(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/updateDeptHead/{id}")
	public ResponseEntity<?> updateDeptHead(@PathVariable Long id, @RequestBody DepartmentHeadDTO departmentHeadDTO) {
		try {
			companyService.updateDeptHead(id, departmentHeadDTO);
			return ResponseEntity.ok("Department Head Details Updated successfully.");
		}catch(UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department Head not found.");
        }catch(Exception e){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Updating Department Head Details.");
		}
	}

	@GetMapping("/getDeptHead/{id}")
	public ResponseEntity<?> getDeptHeadDetails(@PathVariable Long id){
		DepartmentHead deptHead = companyService.getDeptHeadDetails(id);
		return new ResponseEntity<>(deptHead, HttpStatus.OK);
	}
}