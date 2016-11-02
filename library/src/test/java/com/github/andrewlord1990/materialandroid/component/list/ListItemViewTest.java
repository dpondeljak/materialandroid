/*
 *  Copyright (C) 2016 Andrew Lord
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *  the License.
 *
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package com.github.andrewlord1990.materialandroid.component.list;

import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;

import com.github.andrewlord1990.materialandroid.R;
import com.github.andrewlord1990.materialandroid.robolectric.LibraryRobolectricTestRunner;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RuntimeEnvironment;

import static com.github.andrewlord1990.materialandroid.component.list.ListItemViewAssert.assertThat;

@RunWith(LibraryRobolectricTestRunner.class)
public class ListItemViewTest {

  private static final String PRIMARY = "somePrimaryText";
  private static final String SECONDARY = "someSecondaryText";
  private static final String TERTIARY = "someTertiaryText";

  private ListItemView listItemView;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    LayoutParams params = new LayoutParams(
        LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

    listItemView = new ListItemView(RuntimeEnvironment.application);
    listItemView.setLayoutParams(params);
    listItemView.layout(0, 0, 500, 1000);
  }

  @Test
  public void givenContext_whenCreated_thenDefaultVariant() {
    ListItemView view = new ListItemView(RuntimeEnvironment.application);

    assertThat(view).hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT);
  }

  @Test
  public void givenAttrsWithNoCustomAttributes_whenCreated_thenSetupWithDefaults() {
    AttributeSet attrs = Robolectric.buildAttributeSet().build();

    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs);

    assertThat(view).hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT);
  }

  @Test
  public void givenVariantInAttrs_whenCreated_thenHasVariant() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_list_item_variant, "two_line_text_icon")
        .build();

    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs);

    assertThat(view)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasIconView();
  }

  @Test
  public void givenTextsInAttrs_whenCreated_thenHasTexts() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_list_item_variant, "three_line_text")
        .addAttribute(R.attr.ma_list_text_primary, PRIMARY)
        .addAttribute(R.attr.ma_list_text_secondary, SECONDARY)
        .addAttribute(R.attr.ma_list_text_tertiary, TERTIARY)
        .build();

    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs);

    assertThat(view)
        .hasPrimaryText(PRIMARY)
        .hasSecondaryText(SECONDARY)
        .hasTertiaryText(TERTIARY)
        .hasSecondaryTextView()
        .hasTertiaryTextView();
  }

  @Test
  public void givenIconInAttrs_whenCreated_thenHasIcon() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_list_item_variant, "one_line_text_icon")
        .addAttribute(R.attr.ma_list_icon, "@drawable/ic_icon_square")
        .build();

    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs, 0);

    assertThat(view)
        .hasIcon(R.drawable.ic_icon_square)
        .hasIconView();
  }

  @Test
  public void givenAvatarInAttrs_whenCreated_thenHasAvatar() {
    AttributeSet attrs = Robolectric.buildAttributeSet()
        .addAttribute(R.attr.ma_list_item_variant, "one_line_text_avatar")
        .addAttribute(R.attr.ma_list_avatar, "@drawable/ic_avatar_circle")
        .build();

    ListItemView view = new ListItemView(RuntimeEnvironment.application, attrs, 0, 0);

    assertThat(view)
        .hasAvatar(R.drawable.ic_avatar_circle)
        .hasAvatarView();
  }

  @Test
  public void givenOneLineText_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT)
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenOneLineTextIcon_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON)
        .hasIconView()
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenOneLineTextAvatar_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_AVATAR);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT_AVATAR)
        .hasAvatarView()
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenOneLineTextIconAndAvatar_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON_AVATAR);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON_AVATAR)
        .hasIconView()
        .hasAvatarView()
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView();
  }

  @Test
  public void givenTwoLineText_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT)
        .hasSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenTwoLineTextIcon_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasIconView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenTwoLineTextAvatar_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT_AVATAR);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_AVATAR)
        .hasSecondaryTextView()
        .hasAvatarView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenTwoLineTextIconAndAvatar_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON_AVATAR);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_TWO_LINE_TEXT_ICON_AVATAR)
        .hasSecondaryTextView()
        .hasIconView()
        .hasAvatarView()
        .doesNotHaveTertiaryTextView();
  }

  @Test
  public void givenThreeLineText_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenThreeLineTextIcon_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .hasIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenThreeLineTextAvatar_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT_AVATAR);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT_AVATAR)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .hasAvatarView()
        .doesNotHaveIconView();
  }

  @Test
  public void givenThreeLineTextIconAndAvatar_whenSetVariant_thenVariantChanged() {
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON_AVATAR);

    assertThat(listItemView)
        .hasVariant(ListItemView.VARIANT_THREE_LINE_TEXT_ICON_AVATAR)
        .hasSecondaryTextView()
        .hasTertiaryTextView()
        .hasIconView()
        .hasAvatarView();
  }

  @Test
  @SuppressWarnings("WrongConstant")
  public void givenInvalidVariant_whenSetVariant_thenHasDefaultVariant() {
    listItemView.setVariant(10000);

    assertThat(listItemView)
        .doesNotHaveSecondaryTextView()
        .doesNotHaveTertiaryTextView()
        .doesNotHaveIconView()
        .doesNotHaveAvatarView();
  }

  @Test
  public void givenStringResource_whenSetPrimaryText_thenHasPrimaryText() {
    listItemView.setPrimaryText(R.string.list_item_avatar_cd);

    assertThat(listItemView)
        .hasPrimaryText("Avatar");
  }

  @Test
  public void whenSetPrimaryText_thenHasPrimaryText() {
    final String expectedText = "someText";

    listItemView.setPrimaryText(expectedText);

    assertThat(listItemView)
        .hasPrimaryText(expectedText);
  }

  @Test
  public void givenNoSecondaryTextView_whenGetSecondaryText_thenNull() {
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT);

    CharSequence actual = listItemView.getSecondaryText();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenStringResource_whenSetSecondaryText_thenHasSecondaryText() {
    listItemView.setSecondaryText(R.string.list_item_icon_cd);
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(listItemView)
        .hasSecondaryText("Icon");
  }

  @Test
  public void whenSetSecondaryText_thenHasSecondaryText() {
    final String expectedText = "someText";

    listItemView.setSecondaryText(expectedText);
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    assertThat(listItemView)
        .hasSecondaryText(expectedText);
  }

  @Test
  public void givenNoTertiaryTextView_whenGetTertiaryText_thenNull() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    CharSequence actual = listItemView.getTertiaryText();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenStringResource_whenSetTertiaryText_thenHasTertiaryText() {
    listItemView.setTertiaryText(R.string.list_item_avatar_cd);
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT);

    assertThat(listItemView)
        .hasTertiaryText("Avatar");
  }

  @Test
  public void whenSetTertiaryText_thenHasTertiaryText() {
    final String expectedText = "someText";

    listItemView.setTertiaryText(expectedText);
    listItemView.setVariant(ListItemView.VARIANT_THREE_LINE_TEXT);

    assertThat(listItemView)
        .hasTertiaryText(expectedText);
  }

  @Test
  public void givenNoIconView_whenGetIcon_thenNull() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    Drawable actual = listItemView.getIcon();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenDrawableResource_whenSetIcon_thenHasIcon() {
    listItemView.setIcon(R.drawable.ic_icon_square);
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_ICON);

    assertThat(listItemView).hasIcon(R.drawable.ic_icon_square);
  }

  @Test
  public void givenNoAvatarView_whenGetAvatar_thenNull() {
    listItemView.setVariant(ListItemView.VARIANT_TWO_LINE_TEXT);

    Drawable actual = listItemView.getAvatar();

    Assertions.assertThat(actual).isNull();
  }

  @Test
  public void givenDrawableResource_whenSetAvatar_thenHasAvatar() {
    listItemView.setAvatar(R.drawable.ic_avatar_circle);
    listItemView.setVariant(ListItemView.VARIANT_ONE_LINE_TEXT_AVATAR);

    assertThat(listItemView).hasAvatar(R.drawable.ic_avatar_circle);
  }

}