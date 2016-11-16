package se.frostdigital.badgelayoutapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DiagonalLineView extends View {

	private int dividerColor;
	private Paint paint;

	public DiagonalLineView(Context context)
	{
		super(context);
		init(context);
	}

	public DiagonalLineView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}

	public DiagonalLineView(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context)
	{
		dividerColor = context.getResources().getColor(R.color.dividerColor);

		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(dividerColor);
		paint.setStrokeWidth(1);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawLine(0, getHeight(), getWidth(), 0, paint);
		canvas.drawLine(0, 0, getWidth(), getHeight(), paint);
	}

}