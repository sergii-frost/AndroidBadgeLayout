package se.frostdigital.badgelayoutapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * BadgeTest
 * Created by Sergii Nezdolii on 2016-11-10.
 *
 * Copyright (c) 2016 Frost°. All rights reserved.
 */
public class BadgeLayout extends RelativeLayout {

	/**
	 * Default weight is 0.25 of layout width
	 */
	private static final float DEFAULT_BADGE_WEIGHT = 0.25f;

	/**
	 * Helper to calculate margin and X/Y translations for different positions
	 */
	enum BadgePosition {

		TopLeft(1),
		TopRight(2),
		BottomLeft(3),
		BottomRight(4);

		private int positionId;

		BadgePosition(int id) {
			this.positionId = id;
		}

		public int getValue() {
			return this.positionId;
		}

		public int getRotation() {
			switch (this) {
				case TopLeft:
				case BottomRight:
					return -45;
				case TopRight:
				case BottomLeft:
					return 45;
				default:
					return 0;
			}
		}

		public int getMarginOffset(int width, float weight) {
			return (int)(width*weight/2);
		}

		public int getTranslationXOffset(int width, float weight) {
			int margin = getMarginOffset(width, weight);
			switch (this) {
				case TopLeft:
				case BottomLeft:
					return -(width/2-margin);
				case TopRight:
				case BottomRight:
					return (width/2-margin);
				default:
					return 0;
			}
		}

		public int getTranslationYOffset(int height) {
			switch (this) {
				case TopLeft:
				case TopRight:
					return -height/2;
				case BottomLeft:
				case BottomRight:
					return height/2;
				default:
					return 0;
			}
		}


		public static BadgePosition from(int id) {
			for (BadgePosition value : BadgePosition.values()) {
				if (value.positionId == id) {
					return value;
				}
			}
			return TopLeft;
		}
	}

	private BadgePosition badgePosition;
	private float badgeWeight;

	public BadgeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray customAttrs = context.obtainStyledAttributes(attrs, R.styleable.BadgeLayout);
		try {
			this.badgePosition = BadgePosition.from(customAttrs.getInt(R.styleable.BadgeLayout_badge_position, BadgePosition.TopLeft.getValue()));
			this.badgeWeight = customAttrs.getFloat(R.styleable.BadgeLayout_badge_weight, DEFAULT_BADGE_WEIGHT);
		} finally {
			customAttrs.recycle();
		}
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		setTranslationX(this.badgePosition.getTranslationXOffset(getMeasuredWidth(), badgeWeight));
		setTranslationY(this.badgePosition.getTranslationYOffset(getMeasuredHeight()));
		setRotation(this.badgePosition.getRotation());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int marginOffset = this.badgePosition.getMarginOffset(getMeasuredWidth(), badgeWeight);
		RelativeLayout.LayoutParams layoutParams = (LayoutParams) getLayoutParams();
		switch (this.badgePosition) {
			case TopLeft:
			case TopRight:
				layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
				layoutParams.topMargin = marginOffset;
				break;
			case BottomLeft:
			case BottomRight:
				layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
				layoutParams.bottomMargin = marginOffset;
				break;
		}
		setLayoutParams(layoutParams);
	}
}
