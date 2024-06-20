# Bug Report for Apache Wicket

## Description

This document outlines a bug found in the `DefaultMarkupIdGenerator` of Apache Wicket. The generator is producing non-unique IDs when used in development mode.

## Steps to reproduce

1. See [DefaultMarkupIdGeneratorTest](./src/test/java/de/dietz/DefaultMarkupIdGeneratorTest.java) for a simple example.
2. See [TestHomePage](./src/test/java/de/dietz/TestHomePage.java) for an example synthetic page.

## Expected behavior

The `DefaultMarkupIdGenerator` should produce unique IDs, regardless of the RuntimeConfigurationType.

## Actual behavior

The `DefaultMarkupIdGenerator` is generating non-unique IDs.

## Possible solution

Patch `DefaultMarkupIdGenerator` to include a separator between `markupIdPrefix` and `markupIdPostfix`.
See [PatchedDefaultMarkupIdGenerator](./src/main/java/de/dietz/PatchedDefaultMarkupIdGenerator.java)
