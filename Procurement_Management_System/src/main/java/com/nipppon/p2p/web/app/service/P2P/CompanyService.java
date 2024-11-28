package com.nipppon.p2p.web.app.service.P2P;

import java.util.List;
import java.util.Optional;

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

public interface CompanyService{
//for Division Master
	Optional<Division> findByDivisionNameAndDeleteStatus(String divisionName, int deleteStatus);
	Division saveDivision(DivisionDTO divisionDTO);
	List<DivisionDTO> getDivisionsWithCreatorDetails();
	List<DivisionDTO> getAllDivisions();
	void deleteDivision(Long id);

//for Category Master
	Optional<Category> findByCategoryNameAndDeleteStatus(String categoryName, int deleteStatus);
	Category saveCategory(CategoryDTO categoryDTO);
	List<CategoryDTO> getCategoriesWithCreatorDetails();
	List<CategoryDTO> getAllCategories();
	List<CategoryDTO> getAllCategoriesByPurchaseType(String purType);
	void deleteCategory(Long id);

//for Sub-Category Master
	Optional<SubCategory> findBySubCategoryNameAndCatIdAndDeleteStatus(String subCategoryName,Long categoryId, int deleteStatus);
	SubCategory saveSubCategory(SubCategoryDTO subCategoryDTO);
	List<SubCategoryDTO> getSubCategoriesWithCreatorDetails();
	List<SubCategoryDTO> getAllSubCategories();
	List<SubCategoryDTO> getAllSubCategoriesById(Long id, int deleteStatus);
	void deleteSubCategory(Long id);

//for UOM Master
	Optional<UOM> findByUomNameAndDeleteStatus(String uomName, int deleteStatus);
	UOM saveUOM(UOMDTO uomDTO);
	List<UOMDTO> getUOMWithCreatorDetails();
	List<UOMDTO> getAllUOMs();
	void deleteUOM(Long id);

//for HSN/SAC Master
	Optional<HSN_SAC> findByHsnCodeAndDeleteStatus(String hsnCode, int deleteStatus);
	HSN_SAC saveHSN(HSN_SACDTO hsn_SACDTO);
	List<HSN_SACDTO> getHSNWithCreatorDetails();
	List<HSN_SACDTO> getAllHSNs();
	void deleteHSN(Long id);

//for Purchase Type Table without Master Front End Screen
	List<PurchaseTypeDTO> getAllPurTypes();

//for Product Master
	String getProductCode(Long divName);
	Optional<Product> findByProductDetailsAndDeleteStatus(String prodName, Long divId, String purType, Long catId, Long subCatId, int deleteStatus);
	Product saveProduct(ProductDTO productDTO);
	List<ProductDTO> getProductsWithCreatorDetails();
	Product getProductDetails(Long id);
	ProductDTO viewProductDetails(Long id);
	void updateProduct(Long id, ProductDTO productDTO);
	void deleteProduct(Long id);

//for Department Head Master
	Optional<DepartmentHead> findByDeptHeadAndDeleteStatus(int branchId, int deptId, Long deptHeadId, int deleteStatus);
	DepartmentHead saveDeptHead(DepartmentHeadDTO departmentHeadDTO);
	List<DepartmentHeadDTO> getDeptHeadsWithCreatorDetails(int bId);
	DepartmentHead getDeptHeadDetails(Long id);
	List<ProductDTO> getProducts(Long id, String purType);
	void updateDeptHead(Long id, DepartmentHeadDTO departmentHeadDTO);
	void deleteDeptHead(Long id);
}