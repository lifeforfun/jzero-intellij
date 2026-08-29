package io.jzero.editor;

import com.intellij.lang.Language;
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
import io.jzero.language.ApiLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Fixed indent for .api — avoid formatter-driven full re-parse during indent detection. */
public class ApiCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {

    @NotNull
    @Override
    public Language getLanguage() {
        return ApiLanguage.INSTANCE;
    }

    @Override
    public String getCodeSample(SettingsType settingsType) {
        return "syntax = \"v1\"\n\ntype Foo {\n    Name string\n}";
    }

    @Override
    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer,
                                  @NotNull SettingsType settingsType) {
    }

    @Nullable
    @Override
    public CommonCodeStyleSettings getDefaultCommonSettings() {
        CommonCodeStyleSettings settings = new CommonCodeStyleSettings(getLanguage());
        settings.initIndentOptions();
        CommonCodeStyleSettings.IndentOptions indent = settings.getIndentOptions();
        indent.INDENT_SIZE = 4;
        indent.CONTINUATION_INDENT_SIZE = 4;
        indent.TAB_SIZE = 4;
        indent.USE_TAB_CHARACTER = false;
        return settings;
    }
}
