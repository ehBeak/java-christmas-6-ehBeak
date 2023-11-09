package christmas.model.menu;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTest {

    @DisplayName("메뉴이름이 존재하지 않는다면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(strings = {"피자", "치킨"})
    void notExistMenuThrowException(String menuName) {
        assertThatThrownBy(() -> Menu.Of(menuName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    static Stream<Arguments> createMenuNameAndMenu() {
        return Stream.of(
                arguments("양송이수프", Menu.BUTTON_MUSHROOM_SOUP),
                arguments("티본스테이크", Menu.T_BONE_STEAK),
                arguments("초코케이크", Menu.CHOCOLATE_CAKE),
                arguments("제로콜라", Menu.ZERO_COLA)
        );
    }

    @DisplayName("메뉴이름이 존재한다면 해당 메뉴를 반환한다.")
    @ParameterizedTest
    @MethodSource("createMenuNameAndMenu")
    void existMenuReturnMenu(String menuName, Menu expectedMenu) {
        Menu menu = Menu.Of(menuName);

        assertThat(menu).isEqualTo(expectedMenu);
    }

}