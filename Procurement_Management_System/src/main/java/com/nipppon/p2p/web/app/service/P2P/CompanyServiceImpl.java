package com.nipppon.p2p.web.app.service.P2P;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nipppon.p2p.web.app.dto.P2P.Masters.CategoryDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.DepartmentHeadDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.DivisionDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.HSN_SACDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.ProductDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.PurchaseTypeDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.SubCategoryDTO;
import com.nipppon.p2p.web.app.dto.P2P.Masters.UOMDTO;
import com.nipppon.p2p.web.app.entity.HRMS.BranchMaster;
import com.nipppon.p2p.web.app.entity.HRMS.Department;
import com.nipppon.p2p.web.app.entity.HRMS.User;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Category;
import com.nipppon.p2p.web.app.entity.P2P.Masters.DepartmentHead;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Division;
import com.nipppon.p2p.web.app.entity.P2P.Masters.HSN_SAC;
import com.nipppon.p2p.web.app.entity.P2P.Masters.Product;
import com.nipppon.p2p.web.app.entity.P2P.Masters.PurchaseType;
import com.nipppon.p2p.web.app.entity.P2P.Masters.SubCategory;
import com.nipppon.p2p.web.app.entity.P2P.Masters.UOM;
import com.nipppon.p2p.web.app.repository.HRMS.BranchMasterRepository;
import com.nipppon.p2p.web.app.repository.HRMS.DepartmentRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.CategoryRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.DepartmentHeadRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.DivisionRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.HSN_SACRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.ProductRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.PurchaseTypeRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.SubCategoryRepository;
import com.nipppon.p2p.web.app.repository.P2P.Masters.UOMRepository;
import com.nipppon.p2p.web.app.service.HRMS.UserService;

@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private DivisionRepository divisionRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private UOMRepository uomRepository;

	@Autowired
	private HSN_SACRepository hsn_SACRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private PurchaseTypeRepository purchaseTypeRepository;

	@Autowired
	private DepartmentHeadRepository departmentHeadRepository;

	@Autowired
	private BranchMasterRepository branchMasterRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private UserService userService;

	@Override
	public Optional<Division> findByDivisionNameAndDeleteStatus(String divisionName, int deleteStatus) {
		return divisionRepository.findByDivisionNameAndDivDeleteStatus(divisionName, deleteStatus);
	}

	@Override
	public Division saveDivision(DivisionDTO divisionDTO) {
		Division division = new Division();
		division.setDivisionName(divisionDTO.getDivisionName());
		division.setDivisionDesc(divisionDTO.getDivisionDesc());
		division.setCreatedBy(divisionDTO.getCreatedBy());
		division.setCreatedOn(divisionDTO.getCreatedOn());
		division.setDivDeleteStatus(divisionDTO.getDivDeleteStatus());
		return divisionRepository.save(division);
	}

	@Override
	public List<DivisionDTO> getDivisionsWithCreatorDetails() {
		List<Division> divisions = divisionRepository.findAll();
		return divisions.stream().filter(div -> div.getDivDeleteStatus() != 1)
			.map(division -> {
				Optional<User> userOpt = userService.findByEmpId(division.getCreatedBy());
				return new DivisionDTO(
					division.getId(),
					division.getDivisionName(),
					division.getDivisionDesc(),
					userOpt.map(User::getEmpId).orElse(null),
					division.getCreatedOn(),
					division.getDivDeleteStatus(),
					userOpt.map(User::getUserName).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public void deleteDivision(Long id) {
		Optional<Division> divisions = divisionRepository.findById(id);
		if (divisions.isPresent()) {
			Division division = divisions.get();
			division.setDivDeleteStatus(1);
			divisionRepository.save(division);
		} else {
			throw new RuntimeException("Division Not Found");
		}
	}

	@Override
	public List<DivisionDTO> getAllDivisions() {
		List<Division> listOfDivision = divisionRepository.findAll();
		return listOfDivision.stream()
				.filter(div -> div.getDivDeleteStatus() != 1)
				.map(divs -> {
					return new DivisionDTO(divs.getId(), divs.getDivisionName(), null, null, null, 0, null);
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<Category> findByCategoryNameAndDeleteStatus(String categoryName, int deleteStatus) {
		return categoryRepository.findByCategoryNameAndCatDeleteStatus(categoryName, deleteStatus);
	}

	@Override
	public Category saveCategory(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setCategoryName(categoryDTO.getCategoryName());
		category.setCategoryDesc(categoryDTO.getCategoryDesc());
		category.setPurchaseType(categoryDTO.getPurchaseType());
		category.setCreatedBy(categoryDTO.getCreatedBy());
		category.setCreatedOn(categoryDTO.getCreatedOn());
		category.setCatDeleteStatus(categoryDTO.getCatDeleteStatus());
		return categoryRepository.save(category);
	}

	@Override
	public List<CategoryDTO> getCategoriesWithCreatorDetails() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().filter(cat -> cat.getCatDeleteStatus() != 1)
			.map(category -> {
				Optional<User> userOpt = userService.findByEmpId(category.getCreatedBy());
				return new CategoryDTO(
					category.getId(),
					category.getCategoryName(),
					category.getCategoryDesc(),
					userOpt.map(User::getEmpId).orElse(null),
					category.getCreatedOn(),
					category.getCatDeleteStatus(),
					userOpt.map(User::getUserName).orElse(null),
					category.getPurchaseType()
				);
			}).collect(Collectors.toList());
	}

	@Override
	public void deleteCategory(Long id) {
		Optional<Category> categories = categoryRepository.findById(id);
		if (categories.isPresent()) {
			Category category = categories.get();
			category.setCatDeleteStatus(1);
			categoryRepository.save(category);
		} else {
			throw new RuntimeException("Category Not Found");
		}
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> listOfCategory = categoryRepository.findAll();
		return listOfCategory.stream()
				.filter(cat -> cat.getCatDeleteStatus() != 1)
				.map(cats -> {
					return new CategoryDTO(cats.getId(), cats.getCategoryName(), null, null, null, 0, null, null);
				}).collect(Collectors.toList());
	}
	
	@Override
	public List<CategoryDTO> getAllCategoriesByPurchaseType(String purType) {
		List<Category> listOfCategory = categoryRepository.findByPurchaseType(purType);
		return listOfCategory.stream()
				.filter(cat -> cat.getCatDeleteStatus() != 1)
				.map(cats -> {
					return new CategoryDTO(cats.getId(), cats.getCategoryName(), null, null, null, 0, null, null);
				}).collect(Collectors.toList());
	}

	@Override
	public Optional<SubCategory> findBySubCategoryNameAndCatIdAndDeleteStatus(String subCategoryName, Long categoryId, int deleteStatus) {
		return subCategoryRepository.findBySubCategoryNameAndCategoryIdAndSubCatDeleteStatus(subCategoryName, categoryId, deleteStatus);
	}
	
	@Override
	public SubCategory saveSubCategory(SubCategoryDTO subCategoryDTO) {
		SubCategory subCategory = new SubCategory();
		subCategory.setSubCategoryName(subCategoryDTO.getSubCategoryName());
		subCategory.setSubCategoryDesc(subCategoryDTO.getSubCategoryDesc());
		subCategory.setCategoryId(subCategoryDTO.getCategoryId());
		subCategory.setCreatedBy(subCategoryDTO.getCreatedBy());
		subCategory.setCreatedOn(subCategoryDTO.getCreatedOn());
		subCategory.setSubCatDeleteStatus(subCategoryDTO.getSubCatDeleteStatus());
		return subCategoryRepository.save(subCategory);
	}

	@Override
	public List<SubCategoryDTO> getSubCategoriesWithCreatorDetails() {
		List<SubCategory> subCategories = subCategoryRepository.findAll();
		return subCategories.stream().filter(subCat -> subCat.getSubCatDeleteStatus() != 1)
			.map(subCategory -> {
				Optional<User> userOpt = userService.findByEmpId(subCategory.getCreatedBy());
				Optional<Category> categoryName = categoryRepository.findById(subCategory.getCategoryId());
				return new SubCategoryDTO(
					subCategory.getId(),
					subCategory.getSubCategoryName(),
					subCategory.getSubCategoryDesc(),
					subCategory.getCategoryId(),
					userOpt.map(User::getEmpId).orElse(null),
					subCategory.getCreatedOn(),
					subCategory.getSubCatDeleteStatus(),
					userOpt.map(User::getUserName).orElse(null),
					categoryName.map(Category :: getCategoryName).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public void deleteSubCategory(Long id) {
		Optional<SubCategory> subCategories = subCategoryRepository.findById(id);
		if (subCategories.isPresent()) {
			SubCategory subCategory = subCategories.get();
			subCategory.setSubCatDeleteStatus(1);
			subCategoryRepository.save(subCategory);
		} else {
			throw new RuntimeException("Sub-Category Not Found");
		}
	}

	@Override
	public List<SubCategoryDTO> getAllSubCategories() {
		List<SubCategory> listOfSubCategory = subCategoryRepository.findAll();
		return listOfSubCategory.stream()
			.filter(subCat -> subCat.getSubCatDeleteStatus() != 1)
			.map(subCats -> {
				return new SubCategoryDTO(subCats.getId(), subCats.getSubCategoryName(), null, null, null, null, 0, null, null);
			}).collect(Collectors.toList());
	}

	@Override
	public List<SubCategoryDTO> getAllSubCategoriesById(Long id, int deleteStatus) {
		List<SubCategory> listOfSubCategory = subCategoryRepository.findByCategoryIdAndSubCatDeleteStatus(id, deleteStatus);
		return listOfSubCategory.stream()
			.map(subCats -> {
				return new SubCategoryDTO(subCats.getId(), subCats.getSubCategoryName(), null, null, null, null, 0, null, null);
			}).collect(Collectors.toList());
	}

	@Override
	public Optional<UOM> findByUomNameAndDeleteStatus(String uomName, int deleteStatus) {
		return uomRepository.findByUomNameAndUomDeleteStatus(uomName, deleteStatus);
	}

	@Override
	public UOM saveUOM(UOMDTO uomDTO) {
		UOM uom = new UOM();
		uom.setUomName(uomDTO.getUomName());
		uom.setUomDesc(uomDTO.getUomDesc());
		uom.setCreatedBy(uomDTO.getCreatedBy());
		uom.setCreatedOn(uomDTO.getCreatedOn());
		uom.setUomDeleteStatus(uomDTO.getUomDeleteStatus());
		return uomRepository.save(uom);
	}

	@Override
	public List<UOMDTO> getUOMWithCreatorDetails() {
		List<UOM> uoms = uomRepository.findAll();
		return uoms.stream().filter(u -> u.getUomDeleteStatus() != 1)
			.map(uom -> {
				Optional<User> userOpt = userService.findByEmpId(uom.getCreatedBy());
				return new UOMDTO(
					uom.getId(),
					uom.getUomName(),
					uom.getUomDesc(),
					userOpt.map(User::getEmpId).orElse(null),
					uom.getCreatedOn(),
					uom.getUomDeleteStatus(),
					userOpt.map(User::getUserName).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public void deleteUOM(Long id) {
		Optional<UOM> uoms = uomRepository.findById(id);
		if (uoms.isPresent()) {
			UOM uom = uoms.get();
			uom.setUomDeleteStatus(1);
			uomRepository.save(uom);
		} else {
			throw new RuntimeException("UOM Not Found");
		}
	}

	@Override
	public List<UOMDTO> getAllUOMs() {
		List<UOM> listOfUom = uomRepository.findAll();
		return listOfUom.stream()
			.filter(uom -> uom.getUomDeleteStatus() != 1)
			.map(uoms -> {
				return new UOMDTO(uoms.getId(), uoms.getUomName(), null, null, null, 0, null);
			}).collect(Collectors.toList());
	}

	@Override
	public Optional<HSN_SAC> findByHsnCodeAndDeleteStatus(String hsnCode, int deleteStatus) {
		return hsn_SACRepository.findByHsnCodeAndHsnDeleteStatus(hsnCode, deleteStatus);
	}

	@Override
	public HSN_SAC saveHSN(HSN_SACDTO hsn_SACDTO) {
		HSN_SAC hsn_SAC = new HSN_SAC();
		hsn_SAC.setHsnCode(hsn_SACDTO.getHsnCode());
		hsn_SAC.setHsnDesc(hsn_SACDTO.getHsnDesc());
		hsn_SAC.setCreatedBy(hsn_SACDTO.getCreatedBy());
		hsn_SAC.setCreatedOn(hsn_SACDTO.getCreatedOn());
		hsn_SAC.setHsnDeleteStatus(hsn_SACDTO.getHsnDeleteStatus());
		return hsn_SACRepository.save(hsn_SAC);
	}

	@Override
	public List<HSN_SACDTO> getHSNWithCreatorDetails() {
		List<HSN_SAC> hsns = hsn_SACRepository.findAll();
		return hsns.stream().filter(h -> h.getHsnDeleteStatus() != 1)
			.map(hsn -> {
				Optional<User> userOpt = userService.findByEmpId(hsn.getCreatedBy());
				return new HSN_SACDTO(
					hsn.getId(),
					hsn.getHsnCode(),
					hsn.getHsnDesc(),
					userOpt.map(User::getEmpId).orElse(null),
					hsn.getCreatedOn(),
					hsn.getHsnDeleteStatus(),
					userOpt.map(User::getUserName).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public void deleteHSN(Long id) {
		Optional<HSN_SAC> hsns = hsn_SACRepository.findById(id);
		if (hsns.isPresent()) {
			HSN_SAC hsn = hsns.get();
			hsn.setHsnDeleteStatus(1);
			hsn_SACRepository.save(hsn);
		} else {
			throw new RuntimeException("HSN_SAC Code Not Found");
		}
	}

	@Override
	public List<HSN_SACDTO> getAllHSNs() {
		List<HSN_SAC> listOfHsn = hsn_SACRepository.findAll();
		return listOfHsn.stream()
			.filter(hsn -> hsn.getHsnDeleteStatus() != 1)
			.map(hsns -> {
				return new HSN_SACDTO(hsns.getId(), hsns.getHsnCode(), null, null, null, 0, null);
			}).collect(Collectors.toList());
	}

	@Override
	public List<PurchaseTypeDTO> getAllPurTypes(){
		List<PurchaseType> listOfPt = purchaseTypeRepository.findAll();
		return listOfPt.stream()
			.map(pts -> {
				return new PurchaseTypeDTO(pts.getId(), pts.getPurchaseType(), pts.getPurchaseTypeDesc());
			}).collect(Collectors.toList());
	}

	@Override
	public String getProductCode(Long divId) {
	    // Retrieve the product with the maximum productCode for the specified division
	    Product maxProduct = productRepository.findTopByProdDivIdOrderByProductCodeDesc(divId);

	    // Retrieve the Division Name by parameter divId
		Optional<Division> divOpt		= divisionRepository.findById(divId);
	    String divName = divOpt.map(Division :: getDivisionName).orElse(null);

	    // Initialize the new numeric part
	    int newNumber = 1;

	    if(divName.equalsIgnoreCase("IT"))
	    	divName = "I-";
	    else
	    	divName = "N-";

	    if (maxProduct != null && maxProduct.getProductCode() != null) {
	        // Extract the numeric part from the max product code and increment it
	        String numericPart = maxProduct.getProductCode().replaceAll("\\D+", ""); // Remove non-numeric characters
	        newNumber = numericPart.isEmpty() ? 1 : Integer.parseInt(numericPart) + 1;
	    }

	    // Format the new product code as divName + "00001", with zero padding to 5 digits
	    return divName+String.format("%05d", newNumber);
	}

	@Override
	public Optional<Product> findByProductDetailsAndDeleteStatus(String prodName, Long divId, String purType, Long catId, Long subCatId, int deleteStatus){
		return productRepository.findByProductNameAndProdDivIdAndPurchaseTypeAndCategoryIdAndSubCategoryIdAndProdDeleteStatus(prodName, divId, purType, catId, subCatId, deleteStatus);
	}

	@Override
	public Product saveProduct(ProductDTO productDTO){
		Product product = new Product();
		product.setProdDivId(productDTO.getProdDivId());
		product.setPurchaseType(productDTO.getPurchaseType());
		product.setProductCode(productDTO.getProductCode());
		product.setHsnsacId(productDTO.getHsnsacId());
		product.setCategoryId(productDTO.getCategoryId());
		product.setSubCategoryId(productDTO.getSubCategoryId());
		product.setProductName(productDTO.getProductName());
		product.setProductDesc(productDTO.getProductDesc());
		product.setManufacturer(productDTO.getManufacturer());
		product.setModel(productDTO.getModel());
		product.setUomId(productDTO.getUomId());
		product.setPrice(productDTO.getPrice());
		product.setMinQty(productDTO.getMinQty());
		product.setMaxQty(productDTO.getMaxQty());
		product.setCreatedBy(productDTO.getCreatedBy());
		product.setCreatedOn(productDTO.getCreatedOn());
		product.setProdDeleteStatus(productDTO.getProdDeleteStatus());
		return productRepository.save(product);
	}

	@Override
	public List<ProductDTO> getProductsWithCreatorDetails() {
		List<Product> products = productRepository.findAll();
		return products.stream().filter(pro -> pro.getProdDeleteStatus() != 1)
			.map(product -> {
				Optional<User> userOpt 			= userService.findByEmpId(product.getCreatedBy());
				Optional<Division> divOpt		= divisionRepository.findById(product.getProdDivId());
				Optional<Category> catOpt 		= categoryRepository.findById(product.getCategoryId());
				Optional<SubCategory> subCatOpt = subCategoryRepository.findById(product.getSubCategoryId());
				Optional<UOM> uomOpt			= uomRepository.findById(product.getUomId());
				Optional<HSN_SAC> hsnOpt		= hsn_SACRepository.findById(product.getHsnsacId());
				return new ProductDTO(
					product.getId(),
					product.getProductCode(),
					product.getHsnsacId(),
					product.getProductName(),
					product.getProductDesc(),
					product.getCategoryId(),
					product.getSubCategoryId(),
					product.getMinQty(),
					product.getMaxQty(),
					product.getProdDivId(),
					product.getPurchaseType(),
					product.getUomId(),
					product.getPrice(),
					product.getManufacturer(),
					product.getModel(),
					userOpt.map(User :: getEmpId).orElse(null),
					product.getCreatedOn(),
					product.getProdDeleteStatus(),
					userOpt.map(User :: getUserName).orElse(null),
					divOpt.map(Division :: getDivisionName).orElse(null),
					catOpt.map(Category :: getCategoryName).orElse(null),
					subCatOpt.map(SubCategory :: getSubCategoryName).orElse(null),
					uomOpt.map(UOM :: getUomName).orElse(null),
					hsnOpt.map(HSN_SAC :: getHsnCode).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public Product getProductDetails(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Details Not Found"));
	}

	@Override
	public ProductDTO viewProductDetails(Long id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()){
			Optional<HSN_SAC> hsnOpt = hsn_SACRepository.findById(optionalProduct.get().getHsnsacId());
			Optional<Division> divOpt = divisionRepository.findById(optionalProduct.get().getProdDivId());
			Optional<Category> catOpt = categoryRepository.findById(optionalProduct.get().getCategoryId());
			Optional<SubCategory> subCatOpt = subCategoryRepository.findById(optionalProduct.get().getSubCategoryId());
			Optional<UOM> uomOpt = uomRepository.findById(optionalProduct.get().getUomId());
			Optional<User> userOpt = userService.findByEmpId(optionalProduct.get().getCreatedBy());
			return new ProductDTO(
				optionalProduct.get().getId(),
				optionalProduct.get().getProductCode(),
				optionalProduct.get().getHsnsacId(),
				optionalProduct.get().getProductName(),
				optionalProduct.get().getProductDesc(),
				optionalProduct.get().getCategoryId(),
				optionalProduct.get().getSubCategoryId(),
				optionalProduct.get().getMinQty(),
				optionalProduct.get().getMaxQty(),
				optionalProduct.get().getProdDivId(),
				optionalProduct.get().getPurchaseType(),
				optionalProduct.get().getUomId(),
				optionalProduct.get().getPrice(),
				optionalProduct.get().getManufacturer(),
				optionalProduct.get().getModel(),
				userOpt.get().getEmpId(),
				optionalProduct.get().getCreatedOn(),
				optionalProduct.get().getProdDeleteStatus(),
				userOpt.get().getUserName(),
				divOpt.get().getDivisionName(),
				catOpt.get().getCategoryName(),
				subCatOpt.get().getSubCategoryName(),
				uomOpt.get().getUomName(),
				hsnOpt.get().getHsnCode()
			);
		}else{
			throw new RuntimeException("Product Details Not Found");
		}
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> products = productRepository.findById(id);
		if (products.isPresent()) {
			Product product = products.get();
			product.setProdDeleteStatus(1);
			productRepository.save(product);
		} else {
			throw new RuntimeException("Product Details Not Found");
		}
	}

	@Override
	public void updateProduct(Long id, ProductDTO productDTO) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			product.setProdDivId(optionalProduct.get().getProdDivId());
			product.setPurchaseType(productDTO.getPurchaseType());
			product.setProductCode(optionalProduct.get().getProductCode());
			product.setHsnsacId(productDTO.getHsnsacId());
			product.setCategoryId(productDTO.getCategoryId());
			product.setSubCategoryId(productDTO.getSubCategoryId());
			product.setProductName(productDTO.getProductName());
			product.setProductDesc(productDTO.getProductDesc());
			product.setManufacturer(productDTO.getManufacturer());
			product.setModel(productDTO.getModel());
			product.setUomId(productDTO.getUomId());
			product.setPrice(productDTO.getPrice());
			product.setMinQty(productDTO.getMinQty());
			product.setMaxQty(productDTO.getMaxQty());
			product.setCreatedBy(productDTO.getCreatedBy());
			product.setCreatedOn(productDTO.getCreatedOn());
			product.setProdDeleteStatus(optionalProduct.get().getProdDeleteStatus());
			productRepository.save(product);
		}else{
			throw new RuntimeException("Product not found");
		}
	}

	@Override
	public Optional<DepartmentHead> findByDeptHeadAndDeleteStatus(int branchId, int deptId, Long deptHeadId, int deleteStatus) {
		return departmentHeadRepository.findByBranchIdAndDeptIdAndDeptHeadIdAndDeptHeadDeleteStatus(branchId, deptId, deptHeadId, deleteStatus);
	}

	@Override
	public List<ProductDTO> getProducts(Long id, String purType) {
		List<Product> listOfProduct = productRepository.findByProdDivIdAndPurchaseType(id, purType);
		return listOfProduct.stream()
			.filter(prd -> prd.getProdDeleteStatus() != 1)
			.map(prds -> {
				Optional<HSN_SAC> hsnOpt = hsn_SACRepository.findById(prds.getHsnsacId());
				Optional<Division> divOpt = divisionRepository.findById(prds.getProdDivId());
				Optional<Category> catOpt = categoryRepository.findById(prds.getCategoryId());
				Optional<SubCategory> subCatOpt = subCategoryRepository.findById(prds.getSubCategoryId());
				Optional<UOM> uomOpt = uomRepository.findById(prds.getUomId());
				Optional<User> userOpt = userService.findByEmpId(prds.getCreatedBy());
				return new ProductDTO(
					prds.getId(),
					prds.getProductCode(),
					prds.getHsnsacId(),
					prds.getProductName(),
					prds.getProductDesc(),
					prds.getCategoryId(),
					prds.getSubCategoryId(),
					prds.getMinQty(),
					prds.getMaxQty(),
					prds.getProdDivId(),
					prds.getPurchaseType(),
					prds.getUomId(),
					prds.getPrice(),
					prds.getManufacturer(),
					prds.getModel(),
					prds.getCreatedBy(),
					prds.getCreatedOn(),
					prds.getProdDeleteStatus(),
					userOpt.map(User :: getUserName).orElse(null),
					divOpt.map(Division :: getDivisionName).orElse(null),
					catOpt.map(Category :: getCategoryName).orElse(null),
					subCatOpt.map(SubCategory :: getSubCategoryName).orElse(null),
					uomOpt.map(UOM :: getUomName).orElse(null),
					hsnOpt.map(HSN_SAC :: getHsnCode).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public DepartmentHead saveDeptHead(DepartmentHeadDTO departmentHeadDTO) {
		DepartmentHead departmentHead = new DepartmentHead();
		departmentHead.setBranchId(departmentHeadDTO.getBranchId());
		departmentHead.setDeptId(departmentHeadDTO.getDeptId());
		departmentHead.setDeptHeadId(departmentHeadDTO.getDeptHeadId());
		departmentHead.setCreatedBy(departmentHeadDTO.getCreatedBy());
		departmentHead.setCreatedOn(departmentHeadDTO.getCreatedOn());
		departmentHead.setDeptHeadDeleteStatus(departmentHeadDTO.getDeptHeadDeleteStatus());
		return departmentHeadRepository.save(departmentHead);
	}


	@Override
	public List<DepartmentHeadDTO> getDeptHeadsWithCreatorDetails(int bId) {
		List<DepartmentHead> deptHeads = departmentHeadRepository.findAll();
		Optional<BranchMaster> brOpt = branchMasterRepository.findById(bId);
		return deptHeads.stream().filter(deptHead -> deptHead.getDeptHeadDeleteStatus() != 1)
			.filter(bIds -> {
				int branchTypeCode = brOpt.get().getBranchTypeCode();
				return branchTypeCode == 90 || (branchTypeCode != 90 && bId == bIds.getBranchId());
			})
			.map(departHead -> {
				Optional<User> userOpt			= userService.findByEmpId(departHead.getCreatedBy());
				Optional<User> dhnOpt			= userService.findByEmpId((departHead.getDeptHeadId()).toString());
				Optional<BranchMaster> bnOpt	= branchMasterRepository.findById(departHead.getBranchId());
				Optional<Department> dnOpt		= departmentRepository.findById(departHead.getDeptId());
				return new DepartmentHeadDTO(
					departHead.getId(),
					departHead.getBranchId(),
					departHead.getDeptId(),
					departHead.getDeptHeadId(),
					userOpt.map(User :: getEmpId).orElse(null),
					departHead.getCreatedOn(),
					departHead.getDeptHeadDeleteStatus(),
					bnOpt.map(BranchMaster :: getBranchName).orElse(null),
					dnOpt.map(Department :: getDepartmentName).orElse(null),
					dhnOpt.map(User :: getUserName).orElse(null),
					userOpt.map(User :: getUserName).orElse(null)
				);
			}).collect(Collectors.toList());
	}

	@Override
	public DepartmentHead getDeptHeadDetails(Long id) {
		return departmentHeadRepository.findById(id).orElseThrow(() -> new RuntimeException("Department Head Details Not Found"));
	}

	@Override
	public void deleteDeptHead(Long id) {
		Optional<DepartmentHead> depts = departmentHeadRepository.findById(id);
		if (depts.isPresent()) {
			DepartmentHead dept = depts.get();
			dept.setDeptHeadDeleteStatus(1);
			departmentHeadRepository.save(dept);
		} else {
			throw new RuntimeException("Department Head Not Found");
		}
	}

	@Override
	public void updateDeptHead(Long id, DepartmentHeadDTO departmentHeadDTO) {
		Optional<DepartmentHead> optionalDeptHead = departmentHeadRepository.findById(id);
		if(optionalDeptHead.isPresent()) {
			DepartmentHead departmentHead = optionalDeptHead.get();
			departmentHead.setBranchId(optionalDeptHead.get().getBranchId());
			departmentHead.setDeptId(optionalDeptHead.get().getDeptId());
			departmentHead.setDeptHeadId(departmentHeadDTO.getDeptHeadId());
			departmentHead.setCreatedBy(departmentHeadDTO.getCreatedBy());
			departmentHead.setCreatedOn(optionalDeptHead.get().getCreatedOn());
			departmentHead.setDeptHeadDeleteStatus(optionalDeptHead.get().getDeptHeadDeleteStatus());
			departmentHeadRepository.save(departmentHead);
		}else{
			throw new RuntimeException("Department Head not found");
		}
	}
}