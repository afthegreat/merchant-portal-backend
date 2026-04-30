package merchant_backend.dto.category;

public record LoggedInUserCategoryResponseDto(
        Long categoryId,
        Long parentId,
        String parentCategoryName,
        String categoryName
) {
}
