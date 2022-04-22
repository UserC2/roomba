# Driving the Roomba

## Motor Orientations
 (Assuming seat is facing forward)

PWM IDs:
 ```
    3  4
  1  ^^  2
    5  6
```
Wheel Directions:
```
   /   \
  |  ^  |
   \   /
```
Names:
```
   fl fr
  l  ^  r
   rl rr
```
## Driving Forward
  * Set l and r to forward
  * Set fl to forward
  * Set fr to backward (drive against fl)
  * Set rl to backward
  * Set rr to forward (drive against rl)
### Motor Relative Directions
  * `l.set(setpoint)`
  * `r.set(setpoint)`
  * `fl.set(setpoint)`
  * `fr.set(-setpoint)`
  * `rl.set(-setpoint)`
  * `rr.set(setpoint)`
  * That should drive forward/backward
  * Test this

## Driving Left (invert values to drive right)
  * I'm not sure if it's physically capable?
  * Set l and r to 0 (don't need to move)
  * Set fl to backward (ccw)
  * Set fr to backward (ccw)
  * Set rl to forward (cw)
  * Set rr to forward (cw)