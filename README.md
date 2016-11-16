# AndroidBadgeLayout
BadgeLayout allows you to put angled badges in your layout

BadgeLayout inherits from `RelativeLayout` which makes it easy to add whatever you want into it.

By default angle is 45° to make it easier to scale.
There are 2 attributes which can be specified for the `BadgeLayout`:

- `badge_position` [values are `topLeft`, `topRight`, `bottomLeft`, `bottomRight`]
- `badge_weight` which will actually speficy how much of parent layout width badge layout should use (relatively, float value 0.0..1.0)

It may look like this:

![Demo image](https://github.com/sergii-frost/AndroidBadgeLayout/blob/master/screenshots/demo_image.png)


Example of code to use may look like:

Uses default values for attributes (which are `app:badge_position="topLeft", app:badge_weight="0.25"`)

```
		<se.frostdigital.badgelayoutapp.BadgeLayout
			android:layout_width="match_parent"
			android:layout_height="20dp"
			android:background="@color/colorAccent"
			app:badge_position="topLeft"
			app:badge_weight="0.25">
      
      #Your child views are here
		</se.frostdigital.badgelayoutapp.BadgeLayout>
```

In case if custom attrubites need to be specified, do not forget to use add `res-auto` in top layout, like `xmlns:app="http://schemas.android.com/apk/res-auto"`

```
		<se.frostdigital.badgelayoutapp.BadgeLayout
			android:layout_width="match_parent"
			android:layout_height="20dp"
			android:background="@color/colorAccent"
			app:badge_position="bottomLeft"
			app:badge_weight="0.25">

			<TextView
				android:layout_width="wrap_content"
				android:layout_height="wrap_content"
				android:layout_centerInParent="true"
				android:text="Bottom Left"/>

		</se.frostdigital.badgelayoutapp.BadgeLayout>
```
