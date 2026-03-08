
const options = [
  './e2e/features/',
  '--require-module ts-node/register',
  '--require ./e2e/step_definitions/**/*.steps.ts',
  '--require ./e2e/support/hooks.ts'
].join(' ')

module.exports = {
  default: options
}
