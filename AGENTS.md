# Agent Guidelines

Guidelines for AI agents working in this FTC project codebase:

## Code Conventions

- **TeleOp OpModes**: Write TeleOp code by extending `OpMode` (iterative `init()` and `loop()`), **not** `LinearOpMode`.
- **Inline Comments**: Place single-line comments at the end of the line being explained (e.g., `leftMotor.setPower(speed); // Sets motor power`).
- **Hardware Mapping**: Ensure hardware device names in `hardwareMap.get(...)` remain consistent with existing class conventions.
- **Simplicity**: Keep classes concise, clean, and easy to modify.
