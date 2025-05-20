package umc.springboot.service.FoodCategoryService;

import java.util.List;

public interface FoodCategoryQueryService {
    boolean doAllExist(List<Long> ids);
}
