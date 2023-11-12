package christmas.model.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuCategoryTest {

    static Stream<Arguments> createManuAndCategory() {
        return Stream.of(
                arguments(Menu.BUTTON_MUSHROOM_SOUP, MenuCategory.APPETIZER),
                arguments(Menu.T_BONE_STEAK, MenuCategory.MAIN),
                arguments(Menu.CHAMPAGNE, MenuCategory.BEVERAGE)
        );
    }

    @DisplayName("주어진 메뉴가 주어진 카테고리에 해당하면 true를 반환하고 그렇지 않으면 false를 반환한다.")
    @ParameterizedTest
    @MethodSource("createManuAndCategory")
    void notMenuInCategoryReturnFalse(Menu menu, MenuCategory menuCategory) {
        MenuCategory category = MenuCategory.findByMenu(menu);

        assertThat(category).isEqualTo(menuCategory);
    }
}