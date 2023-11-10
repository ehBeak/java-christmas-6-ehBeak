package christmas.model.menu;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class MenuCategoryTest {

    @DisplayName("넘겨준 값이 음료면 true를 반환한다.")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"ZERO_COLA", "RED_WINE", "CHAMPAGNE"})
    void beverageCategoryReturnTrue(Menu menu) {
        Boolean isBeverage = MenuCategory.isBeverageCategory(menu);

        assertThat(isBeverage).isTrue();
    }

    @DisplayName("넘겨준 값이 음료가 아니면 false를 반환한다.")
    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"T_BONE_STEAK", "CHOCOLATE_CAKE", "BUTTON_MUSHROOM_SOUP"})
    void notBeverageCategoryReturnFalse(Menu menu) {
        Boolean isBeverage = MenuCategory.isBeverageCategory(menu);

        assertThat(isBeverage).isFalse();
    }
}