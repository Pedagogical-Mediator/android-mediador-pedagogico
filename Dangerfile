# Sometimes it's a README fix, or something like that - which isn't relevant for
# including in a project's CHANGELOG for example
declared_trivial = gitlab.pr_title.include? "#trivial"

# Make it more obvious that a PR is a work in progress and shouldn't be merged yet
warn("PR is classed as Work in Progress") if gitlab.pr_title.include? "[WIP]"

# Warn when there is a big PR
warn("Big PR") if git.lines_of_code > 500

if gitlab.pr_body.length < 5
  fail "Please provide a summary in the Pull Request description"
end

# AndroidLint
checkstyle_format.base_path = Dir.pwd
checkstyle_format.report "app/build/reports/ktlint/ktlint-debug.xml"

# AndroidLint
android_lint.filtering = true
android_lint.severity = "Error"
android_lint.lint(inline_mode: true)
android_lint.lint