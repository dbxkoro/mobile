package com.bns.mobile.presenter.widget.components.atom

import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.*
import androidx.compose.animation.transition
import androidx.compose.foundation.Interaction
import androidx.compose.foundation.InteractionState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSizeConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSizeIn
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.useOrElse
import androidx.compose.ui.layout.*
import androidx.compose.ui.node.Ref
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.*
import com.bns.mobile.presenter.widget.components.atom.InputStats.*
import kotlin.math.max
import kotlin.math.roundToInt

@Composable
fun PlainTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        textStyle: TextStyle = AmbientTextStyle.current,
        label: @Composable (() -> Unit)? = null,
        placeholder: @Composable (() -> Unit)? = null,
//        leadingIcon: @Composable (() -> Unit)? = null,
//        trailingIcon: @Composable (() -> Unit)? = null,
        isErrorValue: Boolean = false,
//        visualTransformation: VisualTransformation = VisualTransformation.None,
//        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
//        singleLine: Boolean = false,
//        maxLines: Int = Int.MAX_VALUE,
//        onImeActionPerformed: (ImeAction, SoftwareKeyboardController?) -> Unit = { _, _ -> },
        onTextInputStarted: (SoftwareKeyboardController) -> Unit = {},
        interactionState: InteractionState = remember { InteractionState() },
        activeColor: Color = MaterialTheme.colors.primary,
        inactiveColor: Color = MaterialTheme.colors.onSurface,
        errorColor: Color = MaterialTheme.colors.error,
        backgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = androidx.compose.material.ContainerAlpha),
        shape: Shape =
                MaterialTheme.shapes.small.copy(bottomLeft = ZeroCornerSize, bottomRight = ZeroCornerSize)
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = value)) }
    val textFieldValue = textFieldValueState.copy(text = value)

    TextfieldImpl(value = textFieldValue,
            onValueChange = {
                textFieldValueState = it
                if (value != it.text) {
                    onValueChange(it.text)
                }
                            },
            textStyle = textStyle,
            modifier = modifier,
            isErrorValue = isErrorValue,
            label = label,
            placeholder = placeholder,
            onTextInputStarted = onTextInputStarted,
            interactionState = interactionState,
            activeColor = activeColor,
            inactiveColor = inactiveColor,
            errorColor = errorColor,
            backgroundColor = backgroundColor,
            shape = shape
    )
}

@Composable
fun TextfieldImpl(
        value : TextFieldValue,
        onValueChange : (TextFieldValue) -> Unit,
        modifier: Modifier,
        isErrorValue: Boolean,
        textStyle: TextStyle,
        label: @Composable (() -> Unit)?,
        placeholder: @Composable (() -> Unit)?,
        onTextInputStarted: (SoftwareKeyboardController) -> Unit,
        inactiveColor : Color = Color.Gray,
        activeColor : Color = MaterialTheme.colors.primary,
        errorColor : Color = Color.Red,
        interactionState: InteractionState,
        keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
        backgroundColor: Color,
        shape: Shape,

) {
    val textColor = textStyle.color.useOrElse {
        AmbientContentColor.current.copy(alpha = AmbientContentAlpha.current)
    }
    val mergedTextStyle = textStyle.merge(TextStyle(color = textColor))

    val isFocused = interactionState.contains(Interaction.Focused)
    val keyboardController: Ref<SoftwareKeyboardController> = remember { Ref() }

    val inputState = when {
        isFocused -> Focused
        value.text.isEmpty() -> UnfocusedEmpty
        else -> UnfocusedNotEmpty
    }

    val decoratedTextField = @Composable {tagModifier : Modifier ->
        Decoration(
                contentColor = inactiveColor,
                typography = MaterialTheme.typography.subtitle1,
                contentAlpha = ContentAlpha.high
        ) {
            BasicTextField(
                    value = value,
                    modifier = tagModifier.defaultMinSizeConstraints(minWidth = TextFieldMinWidth),
                    textStyle = mergedTextStyle,
                    onValueChange = onValueChange,
                    cursorColor = if (isErrorValue) errorColor else activeColor,
//                    visualTransformation = visualTransformation,
                    keyboardOptions = keyboardOptions,
//                    maxLines = maxLines,
                    interactionState = interactionState,
//                    onImeActionPerformed = {
//                        onImeActionPerformed(it, keyboardController.value)
//                    },
                    onTextInputStarted = {
                        keyboardController.value = it
                        onTextInputStarted(it)
                    },
                    singleLine = true,
            )
        }
    }

    val focusRequester = FocusRequester()
    val textFieldModifier = modifier
            .focusRequester(focusRequester)
            .let {
                it.clickable(interactionState = interactionState, indication = null) {
                    focusRequester.requestFocus()
                    keyboardController.value?.showSoftwareKeyboard()
                }
            }
    TextFieldTransitionScope.Transition(
            inputState = inputState,
            showLabel = label != null,
            activeColor = if (isErrorValue) {
                errorColor
            } else {
                activeColor.applyAlpha(alpha = ContentAlpha.high)
            },
            labelInactiveColor = if (isErrorValue) {
                errorColor
            } else {
                inactiveColor.applyAlpha(alpha = ContentAlpha.medium)
            },
            indicatorInactiveColor = when {
                isErrorValue -> errorColor
//                comment temporary
//                type == TextFieldType.Filled -> inactiveColor.applyAlpha(alpha = IndicatorInactiveAlpha)
                else -> inactiveColor.applyAlpha(alpha = ContentAlpha.disabled)
            }
    ) { labelProgress, animatedLabelColor, indicatorWidth, indicatorColor, placeholderAlpha ->
//        val leadingColor = inactiveColor.applyAlpha(alpha = TrailingLeadingAlpha)
//        val trailingColor = if (isErrorValue) errorColor else leadingColor

        val decoratedLabel: @Composable (() -> Unit)? =
                if (label != null) {
                    @Composable {
                        val labelAnimatedStyle = androidx.compose.ui.text.lerp(
                                MaterialTheme.typography.subtitle1,
                                MaterialTheme.typography.caption,
                                labelProgress
                        )
                        Decoration(
                                contentColor = animatedLabelColor,
                                typography = labelAnimatedStyle,
                                content = label
                        )
                    }
                } else null

        val decoratedPlaceholder : @Composable ((Modifier) -> Unit)? =
                if (placeholder != null && value.text.isEmpty()) {
                    @Composable { modifier ->
                        Box(modifier.alpha(placeholderAlpha)) {
                            Decoration(
                                    contentColor = inactiveColor,
                                    typography = MaterialTheme.typography.subtitle1,
                                    contentAlpha = ContentAlpha.medium,
                                    content = placeholder
                            )
                        }
                    }
                } else null

        TextFieldLayout(
                modifier = Modifier
                        .preferredSizeIn(
                                minWidth = TextFieldMinWidth,
                                minHeight = TextFieldMinHeight
                        )
                        .then(textFieldModifier),
                decoratedTextField = decoratedTextField,
                decoratedPlaceholder = decoratedPlaceholder,
                decoratedLabel = decoratedLabel,
//                leading = leading,
//                trailing = trailing,
//                leadingColor = leadingColor,
//                trailingColor = trailingColor,
                labelProgress = labelProgress,
                indicatorWidth = indicatorWidth,
                indicatorColor = indicatorColor,
                backgroundColor = backgroundColor,
                shape = shape
        )

    }
}

private object TextFieldTransitionScope {
    private val LabelColorProp = ColorPropKey()
    private val LabelProgressProp = FloatPropKey()
    private val IndicatorColorProp = ColorPropKey()
    private val IndicatorWidthProp = DpPropKey()
    private val PlaceholderOpacityProp = FloatPropKey()

    @Composable
    fun Transition(
            inputState : InputStats,
            showLabel: Boolean,
            activeColor: Color,
            labelInactiveColor: Color,
            indicatorInactiveColor: Color,
            content: @Composable (
                    labelProgress: Float,
                    labelColor: Color,
                    indicatorWidth: Dp,
                    indicatorColor: Color,
                    placeholderOpacity: Float
            ) -> Unit
    ){
        val definition = remember(
                showLabel,
                activeColor,
                labelInactiveColor,
                indicatorInactiveColor
        ) {
            generateLabelTransitionDefinition(
                    showLabel,
                    activeColor,
                    labelInactiveColor,
                    indicatorInactiveColor
            )
        }
        val state = transition(definition = definition, toState = inputState)
        content(
                state[LabelProgressProp],
                state[LabelColorProp],
                state[IndicatorWidthProp],
                state[IndicatorColorProp],
                state[PlaceholderOpacityProp]
        )
    }

    private fun generateLabelTransitionDefinition(
            showLabel: Boolean,
            activeColor: Color,
            labelInactiveColor: Color,
            indicatorInactiveColor: Color
    ) = transitionDefinition<InputStats> {

        state(Focused) {
            this[LabelColorProp] = activeColor
            this[IndicatorColorProp] = activeColor
            this[LabelProgressProp] = 1f
            this[IndicatorWidthProp] = IndicatorFocusedWidth
            this[PlaceholderOpacityProp] = 1f
        }
        state(UnfocusedEmpty) {
            this[LabelColorProp] = labelInactiveColor
            this[IndicatorColorProp] = indicatorInactiveColor
            this[LabelProgressProp] = 0f
            this[IndicatorWidthProp] = IndicatorUnfocusedWidth
            this[PlaceholderOpacityProp] = if (showLabel) 0f else 1f
        }
        state(UnfocusedNotEmpty) {
            this[LabelColorProp] = labelInactiveColor
            this[IndicatorColorProp] = indicatorInactiveColor
            this[LabelProgressProp] = 1f
            this[IndicatorWidthProp] = 1.dp
            this[PlaceholderOpacityProp] = 0f
        }

        transition(Focused to UnfocusedEmpty) {
            labelTransition()
            indicatorTransition()
            placeholederDisappearTransition()
        }
        transition(Focused to UnfocusedNotEmpty) {
            indicatorTransition()
        }
        transition(UnfocusedNotEmpty to Focused) {
            indicatorTransition()
        }
        transition(UnfocusedEmpty to Focused) {
            labelTransition()
            indicatorTransition()
            placeholderAppearTransition()
        }
        transition(fromState = UnfocusedNotEmpty, toState = UnfocusedEmpty) {
            labelTransition()
            placeholderAppearTransition()
        }
        transition(fromState = UnfocusedEmpty, toState = UnfocusedNotEmpty) {
            labelTransition()
        }

    }
    private fun TransitionSpec<InputStats>.indicatorTransition() {
        IndicatorColorProp using tween(durationMillis = AnimationDuration)
        IndicatorWidthProp using tween(durationMillis = AnimationDuration)
    }
    private fun TransitionSpec<InputStats>.labelTransition() {
        LabelColorProp using tween(durationMillis = AnimationDuration)
        LabelProgressProp using tween(durationMillis = AnimationDuration)
    }
    private fun TransitionSpec<InputStats>.placeholderAppearTransition() {
        PlaceholderOpacityProp using tween(
            durationMillis = PlaceholderAnimationDuration,
            delayMillis = PlaceholderAnimationDelayOrDuration,
            easing = LinearEasing
        )
    }
    private fun TransitionSpec<InputStats>.placeholederDisappearTransition() {
        PlaceholderOpacityProp using tween(
                durationMillis = PlaceholderAnimationDuration,
                easing = LinearEasing
        )
    }
}

//layout
@Composable
internal fun TextFieldLayout(
        modifier: Modifier = Modifier,
        decoratedTextField: @Composable (Modifier) -> Unit,
        decoratedPlaceholder: @Composable ((Modifier) -> Unit)?,
        decoratedLabel: @Composable (() -> Unit)?,
//        leading: @Composable (() -> Unit)?,
//        trailing: @Composable (() -> Unit)?,
//        leadingColor: Color,
//        trailingColor: Color,
        labelProgress: Float,
        indicatorWidth: Dp,
        indicatorColor: Color,
        backgroundColor: Color,
        shape: Shape
) {
    PlainTextFieldLayout(
            modifier = modifier
                    .background(
                            color = backgroundColor,
                            shape = shape
                    )
                    .drawIndicatorLine(
                            lineWidth = indicatorWidth,
                            color = indicatorColor
                    ),
            textField = decoratedTextField,
            placeholder = decoratedPlaceholder,
            label = decoratedLabel,
//            leading = leading,
//            trailing = trailing,
//            leadingColor = leadingColor,
//            trailingColor = trailingColor,
            animationProgress = labelProgress
    )

}

private val Placeable.nonZero: Boolean get() = this.width != 0 || this.height != 0
internal fun widthOrZero(placeable: Placeable?) = placeable?.width ?: 0
internal fun heightOrZero(placeable: Placeable?) = placeable?.height ?: 0

@Composable
private fun PlainTextFieldLayout(
        modifier: Modifier = Modifier,
        textField: @Composable (Modifier) -> Unit,
        label: @Composable (() -> Unit)?,
        placeholder: @Composable ((Modifier) -> Unit)?,
        animationProgress: Float
) {
    Layout(
            content = {
                val padding = Modifier.padding(horizontal = TextFieldPadding)
                if (placeholder != null) {
                    placeholder(Modifier
                            .layoutId(PlaceholderId)
                            .then(padding))
                }
                if (label != null) {
                    Box(
                            modifier = Modifier
                                    .layoutId(LabelId)
                    ) {
                        label()
                    }
                }
                textField(Modifier
                        .layoutId(TextFieldId)
                        .then(padding))
                      },
            modifier = modifier
    ) { measurables, incomingConstraints ->
        val baseLineOffset = FirstBaselineOffset.toIntPx()
        val bottomPadding = LastBaselineOffset.toIntPx()
        val topPadding = TextFieldTopPadding.toIntPx()
        var occupiedSpaceHorizontally = 0

        // measure leading icon
        val constraints = incomingConstraints.copy(minWidth = 0, minHeight = 0)
        val leadingPlaceable =
                measurables.find { it.layoutId == "leading" }?.measure(constraints)
        occupiedSpaceHorizontally += widthOrZero(
                leadingPlaceable
        )

        // measure trailing icon
        val trailingPlaceable = measurables.find { it.layoutId == "trailing" }
                ?.measure(constraints.offset(horizontal = -occupiedSpaceHorizontally))
        occupiedSpaceHorizontally += widthOrZero(
                trailingPlaceable
        )

        // measure label
        val labelConstraints = constraints
                .offset(
                        vertical = -bottomPadding,
                        horizontal = -occupiedSpaceHorizontally
                )
        val labelPlaceable =
                measurables.find { it.layoutId == LabelId }?.measure(labelConstraints)
        val lastBaseline = labelPlaceable?.get(LastBaseline)?.let {
            if (it != AlignmentLine.Unspecified) it else labelPlaceable.height
        } ?: 0
        val effectiveLabelBaseline = max(lastBaseline, baseLineOffset)

        // measure input field
        val textFieldConstraints = incomingConstraints
                .copy(minHeight = 0)
                .offset(
                        vertical = -bottomPadding - topPadding - effectiveLabelBaseline,
                        horizontal = -occupiedSpaceHorizontally
                )
        val textFieldPlaceable = measurables
                .first { it.layoutId == TextFieldId }
                .measure(textFieldConstraints)

        // measure placeholder
        val placeholderConstraints = textFieldConstraints.copy(minWidth = 0)
        val placeholderPlaceable = measurables
                .find { it.layoutId == PlaceholderId }
                ?.measure(placeholderConstraints)

        val width = calculateWidth(
                leadingPlaceable,
                trailingPlaceable,
                textFieldPlaceable,
                labelPlaceable,
                placeholderPlaceable,
                incomingConstraints
        )
        val height = calculateHeight(
                textFieldPlaceable,
                effectiveLabelBaseline,
                leadingPlaceable,
                trailingPlaceable,
                placeholderPlaceable,
                incomingConstraints,
                density
        )

        layout(width, height) {
            if (widthOrZero(labelPlaceable) != 0) {
                val labelEndPosition =
                        (baseLineOffset - lastBaseline).coerceAtLeast(0)
                place(
                        width,
                        height,
                        layoutDirection,
                        textFieldPlaceable,
                        labelPlaceable,
                        placeholderPlaceable,
                        leadingPlaceable,
                        trailingPlaceable,
                        labelEndPosition,
                        effectiveLabelBaseline + topPadding,
                        animationProgress
                )
            } else {
                // text field should be centered vertically if there is no label
                placeWithoutLabel(
                        width,
                        height,
                        textFieldPlaceable,
                        placeholderPlaceable,
                        leadingPlaceable,
                        trailingPlaceable
                )
            }
        }
    }
}

private fun calculateWidth(
        leadingPlaceable: Placeable?,
        trailingPlaceable: Placeable?,
        textFieldPlaceable: Placeable,
        labelPlaceable: Placeable?,
        placeholderPlaceable: Placeable?,
        constraints: Constraints
): Int {
    val middleSection = maxOf(
            textFieldPlaceable.width,widthOrZero(labelPlaceable),widthOrZero(placeholderPlaceable)
    )
    val wrappedWidth = widthOrZero(leadingPlaceable) + middleSection + widthOrZero(
                    trailingPlaceable
            )
    return max(wrappedWidth, constraints.minWidth)
}

private fun calculateHeight(
        textFieldPlaceable: Placeable,
        labelBaseline: Int,
        leadingPlaceable: Placeable?,
        trailingPlaceable: Placeable?,
        placeholderPlaceable: Placeable?,
        constraints: Constraints,
        density: Float
): Int {
    val bottomPadding = LastBaselineOffset.value * density
    val topPadding = TextFieldTopPadding.value * density
    val inputFieldHeight = max(textFieldPlaceable.height, heightOrZero(placeholderPlaceable))
    val middleSectionHeight = labelBaseline + topPadding + inputFieldHeight + bottomPadding
    return maxOf(
            middleSectionHeight.roundToInt(),
            max(heightOrZero(leadingPlaceable), heightOrZero(trailingPlaceable)),
            constraints.minHeight
    )
}

private fun Modifier.drawIndicatorLine(lineWidth: Dp, color: Color): Modifier {
    return drawBehind {
        val strokeWidth = lineWidth.value * density
        val y = size.height - strokeWidth / 2
        drawLine(
                color,
                Offset(0f, y),
                Offset(size.width, y),
                strokeWidth
        )
    }
}

/**
 * Places the provided text field and placeholder center vertically in [TextField]
 */
private fun Placeable.PlacementScope.placeWithoutLabel(
        width: Int,
        height: Int,
        textPlaceable: Placeable,
        placeholderPlaceable: Placeable?,
        leadingPlaceable: Placeable?,
        trailingPlaceable: Placeable?
) {
    leadingPlaceable?.placeRelative(
            0,
            Alignment.CenterVertically.align(leadingPlaceable.height, height)
    )
    trailingPlaceable?.placeRelative(
            width - trailingPlaceable.width,
            Alignment.CenterVertically.align(trailingPlaceable.height, height)
    )
    textPlaceable.placeRelative(widthOrZero(leadingPlaceable),
            Alignment.CenterVertically.align(textPlaceable.height, height)
    )
    placeholderPlaceable?.placeRelative(widthOrZero(leadingPlaceable),
            Alignment.CenterVertically.align(placeholderPlaceable.height, height)
    )
}

private fun Placeable.PlacementScope.place(
        width: Int,
        height: Int,
        layoutDirection: LayoutDirection,
        textfieldPlaceable: Placeable,
        labelPlaceable: Placeable?,
        placeholderPlaceable: Placeable?,
        leadingPlaceable: Placeable?,
        trailingPlaceable: Placeable?,
        labelEndPosition: Int,
        textPosition: Int,
        animationProgress: Float
) {
    leadingPlaceable?.placeRelative(
            0,
            Alignment.CenterVertically.align(leadingPlaceable.height, height)
    )
    trailingPlaceable?.placeRelative(
            width - trailingPlaceable.width,
            Alignment.CenterVertically.align(trailingPlaceable.height, height)
    )
    if (labelPlaceable != null) {
        val labelCenterPosition = Alignment.CenterStart.align(
                IntSize(labelPlaceable.width, labelPlaceable.height),
                IntSize(width, height),
                layoutDirection
        )
        val labelDistance = labelCenterPosition.y - labelEndPosition
        val labelPositionY =
                labelCenterPosition.y - (labelDistance * animationProgress).roundToInt()
        labelPlaceable.placeRelative(widthOrZero(leadingPlaceable), labelPositionY)
    }
    textfieldPlaceable.placeRelative(widthOrZero(leadingPlaceable), textPosition)
    placeholderPlaceable?.placeRelative(widthOrZero(leadingPlaceable), textPosition)
}

internal fun Color.applyAlpha(alpha: Float): Color {
    return if (this.alpha != 1f) this else this.copy(alpha = alpha)
}

@Composable
internal fun Decoration(
        contentColor: Color,
        typography: TextStyle? = null,
        contentAlpha: Float? = null,
        content: @Composable () -> Unit
) {
    val colorAndEmphasis = @Composable {
        Providers(AmbientContentColor provides contentColor) {
            if (contentAlpha != null) {
                Providers(
                        AmbientContentAlpha provides contentAlpha,
                        content = content
                )
            } else {
                Providers(
                        AmbientContentAlpha provides contentColor.alpha,
                        content = content
                )
            }
        }
    }
    if (typography != null) ProvideTextStyle(typography, colorAndEmphasis) else colorAndEmphasis()
}

private enum class InputStats {
    // Text field is focused
    Focused,

    // Text field is not focused and input text is empty
    UnfocusedEmpty,

    // Text field is not focused but input text is not empty
    UnfocusedNotEmpty
}

internal const val TextFieldId = "TextField"
internal const val PlaceholderId = "Hint"
internal const val LabelId = "Label"

private const val AnimationDuration = 150
private const val PlaceholderAnimationDuration = 83
private const val PlaceholderAnimationDelayOrDuration = 67

private val IndicatorUnfocusedWidth = 1.dp
private val IndicatorFocusedWidth = 2.dp
private const val TrailingLeadingAlpha = 0.54f
private val TextFieldMinHeight = 56.dp
private val TextFieldMinWidth = 280.dp
internal val TextFieldPadding = 0.dp
internal val HorizontalIconPadding = 12.dp

// Filled text field uses 42% opacity to meet the contrast requirements for accessibility reasons
private const val IndicatorInactiveAlpha = 0.42f

/*
This padding is used to allow label not overlap with the content above it. This 8.dp will work
for default cases when developers do not override the label's font size. If they do, they will
need to add additional padding themselves
*/
private val OutlinedTextFieldTopPadding = 8.dp

private val FirstBaselineOffset = 20.dp
private val LastBaselineOffset = 10.dp
private val TextFieldTopPadding = 4.dp
const val ContainerAlpha = 0.12f