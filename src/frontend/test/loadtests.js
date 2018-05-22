'use strict';

require('babel-polyfill');
require('core-js/fn/object/assign');

// Add support for all files in the rest directory
const testsContext = require.context('.', true, /(Test\.js$)|(Helper\.js$)/);
testsContext.keys().forEach(testsContext);
