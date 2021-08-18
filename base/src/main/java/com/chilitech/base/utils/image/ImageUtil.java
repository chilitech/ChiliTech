package com.chilitech.base.utils.image;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * <图片工具类>
 *
 * @author 王乾州
 */
public class ImageUtil {
    /**
     * 图片工具类引用
     */
    private static ImageUtil imageUtil;

    /**
     * 拍照的File
     */
    private File cameraFile;

    /**
     * 相册的RequestCode
     */
    private static final int INTENT_REQUEST_CODE_ALBUM = 0;

    /**
     * 照相的RequestCode
     */
    private static final int INTENT_REQUEST_CODE_CAMERA = 1;

    /**
     * 单例获取构造函数
     *
     * @return
     */
    public static ImageUtil getInstance() {
        if (null == imageUtil) {
            imageUtil = new ImageUtil();
        }
        return imageUtil;
    }

    /**
     * 转换图片成圆形
     *
     * @param bitmap 传入Bitmap对象
     * @return
     */
    public Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * 加载图片 （设置默认图、出错图、是否圆角展示）
     * <p>
     * (加载网络图片)               imageUri = "http://img.daimg.com/uploads/allimg/161031/1-161031001458.jpg";
     * (加载SD card图片)           imageUri = "file:///storage/emulated/0/DCIM/Screenshots/test.png";
     * (加载content provider图片)  imageUri = "content://media/external/audio/albumart/1";
     * (加载assets中的图片)        imageUri = "file:///android_asset/image.png";
     *
     * @param mContxt         传调用页面的上下文 最好传Activity、Fragment、FragmentActivity 因为加载图片跟这三个生命周期绑定了 会提高效率 节省资源
     * @param imageUri        图片地址
     * @param myImageView     图片控件
     * @param defaultResource 默认图片占位符是当请求正在执行时被展示的 Drawable
     * @param errorResource   错误符error Drawable在请求永久性失败时展示。error Drawable 同样也在请求的url/model为null，且并没有设置 fallback drawable时展示
     * @param isRound         true:展示圆角  false:不展示圆角
     */
//    public void showImageView(Context mContxt, String imageUri, ImageView myImageView, int defaultResource, int errorResource, boolean isRound) {
//        if (null == mContxt || null == myImageView) {
//            return;
//        }
//
//        if (mContxt instanceof Activity) {
//            if (((Activity) mContxt).isDestroyed()) {
//                return;
//            }
//        }
//
//        try {
//            RequestOptions requestOptions;
//            if (isRound) {
//                requestOptions = new RequestOptions().placeholder(defaultResource).error(errorResource).transform(new CircleCrop());
//            } else {
//                requestOptions = new RequestOptions().placeholder(defaultResource).error(errorResource);
//            }
//
//            Glide.with(mContxt)
//                    .load(imageUri)
//                    .apply(requestOptions)
//                    .into(myImageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 加载图片 （设置默认图、出错图、是否圆角展示、加载回调函数）
     * <p>
     * (加载网络图片)               imageUri = "http://img.daimg.com/uploads/allimg/161031/1-161031001458.jpg";
     * (加载SD card图片)           imageUri = "file:///storage/emulated/0/DCIM/Screenshots/test.png";
     * (加载content provider图片)  imageUri = "content://media/external/audio/albumart/1";
     * (加载assets中的图片)        imageUri = "file:///android_asset/image.png";
     *
     * @param mContxt         传调用页面的上下文 最好传Activity、Fragment、FragmentActivity 因为加载图片跟这三个生命周期绑定了 会提高效率 节省资源
     * @param imageUri        图片地址
     * @param myImageView     图片控件
     * @param defaultResource 默认图片占位符是当请求正在执行时被展示的 Drawable
     * @param errorResource   错误符error Drawable在请求永久性失败时展示。error Drawable 同样也在请求的url/model为null，且并没有设置 fallback drawable时展示
     * @param isRound         true:展示圆角  false:不展示圆角
     * @param glideListener   加载图片的回调接口
     */
//    public void showImageView(Context mContxt, String imageUri, ImageView myImageView, int defaultResource, int errorResource, boolean isRound, final GlideListener glideListener) {
//        if (null == mContxt || null == myImageView || null == glideListener) {
//            return;
//        }
//
//        if (mContxt instanceof Activity) {
//            if (((Activity) mContxt).isDestroyed()) {
//                return;
//            }
//        }
//
//        try {
//            RequestOptions requestOptions;
//            if (isRound) {
//                requestOptions = new RequestOptions().placeholder(defaultResource).error(errorResource).transform(new CircleCrop());
//            } else {
//                requestOptions = new RequestOptions().placeholder(defaultResource).error(errorResource);
//            }
//
//            Glide.with(mContxt)
//                    .load(imageUri)
//                    .apply(requestOptions)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            glideListener.onLoadFailed(e, model, target, isFirstResource);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            glideListener.onResourceReady(resource, model, target, dataSource, isFirstResource);
//                            return false;
//                        }
//                    })
//                    .into(myImageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 加载图片 （设置默认图、出错图、是否圆角展示、加载回调函数）
     * <p>
     * (加载网络图片)               imageUri = "http://img.daimg.com/uploads/allimg/161031/1-161031001458.jpg";
     * (加载SD card图片)           imageUri = "file:///storage/emulated/0/DCIM/Screenshots/test.png";
     * (加载content provider图片)  imageUri = "content://media/external/audio/albumart/1";
     * (加载assets中的图片)        imageUri = "file:///android_asset/image.png";
     *
     * @param mContxt           传调用页面的上下文 最好传Activity、Fragment、FragmentActivity 因为加载图片跟这三个生命周期绑定了 会提高效率 节省资源
     * @param imageUri          图片地址
     * @param myImageView       图片控件
     * @param defaultResource   默认图片占位符是当请求正在执行时被展示的 Drawable
     * @param errorResource     错误符error Drawable在请求永久性失败时展示。error Drawable 同样也在请求的url/model为null，且并没有设置 fallback drawable时展示
     * @param isRound           true:展示圆角  false:不展示圆角
     * @param imageWidth        宽
     * @param imageHeight       高
     * @param diskCacheStrategy 缓存策略
     * @param priority          优先级
     * @param glideListener     加载图片的回调接口
     */
//    public void showImageView(Context mContxt, String imageUri, ImageView myImageView, int defaultResource, int errorResource, boolean isRound, int imageWidth, int imageHeight, DiskCacheStrategy diskCacheStrategy, Priority priority, final GlideListener glideListener) {
//        if (null == mContxt || null == myImageView || null == glideListener) {
//            return;
//        }
//
//        if (mContxt instanceof Activity) {
//            if (((Activity) mContxt).isDestroyed()) {
//                return;
//            }
//        }
//
//        try {
//            RequestOptions requestOptions;
//            if (isRound) {
//                requestOptions = new RequestOptions().dontAnimate().override(imageWidth, imageHeight).diskCacheStrategy(diskCacheStrategy).priority(priority).placeholder(defaultResource).error(errorResource).transform(new CircleCrop());
//            } else {
//                requestOptions = new RequestOptions().dontAnimate().override(imageWidth, imageHeight).diskCacheStrategy(diskCacheStrategy).priority(priority).placeholder(defaultResource).error(errorResource);
//            }
//            Glide.with(mContxt)
//                    .load(imageUri)
//                    .apply(requestOptions)
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            glideListener.onLoadFailed(e, model, target, isFirstResource);
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            glideListener.onResourceReady(resource, model, target, dataSource, isFirstResource);
//                            return false;
//                        }
//                    })
//                    .into(myImageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 加载图片 （此方法只需传入配置以及Url即可）
     * <p>
     * (加载网络图片)               imageUri = "http://img.daimg.com/uploads/allimg/161031/1-161031001458.jpg";
     * (加载SD card图片)           imageUri = "file:///storage/emulated/0/DCIM/Screenshots/test.png";
     * (加载content provider图片)  imageUri = "content://media/external/audio/albumart/1";
     * (加载assets中的图片)        imageUri = "file:///android_asset/image.png";
     *
     * @param mContxt     传调用页面的上下文 最好传Activity、Fragment、FragmentActivity 因为加载图片跟这三个生命周期绑定了 会提高效率 节省资源
     * @param imageUri    图片地址
     * @param myImageView 图片控件
     * @param options     配置参数
     */
//    public void showImageView(Context mContxt, String imageUri, ImageView myImageView, RequestOptions options) {
//        if (null == mContxt || null == myImageView) {
//            return;
//        }
//
//        if (mContxt instanceof Activity) {
//            if (((Activity) mContxt).isDestroyed()) {
//                return;
//            }
//        }
//
//        try {
//            Glide.with(mContxt)
//                    .load(imageUri)
//                    .apply(options)
//                    .into(myImageView);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 简单图片加载，不需要设置默认图，错误图
     *
     * @param mContxt     上下文
     * @param imageUri    图片地址
     * @param myImageView 图片view
     */
    public void showImageView(Context mContxt, String imageUri, ImageView myImageView) {
        if (null == mContxt || null == myImageView) {
            return;
        }
        if (mContxt instanceof Activity) {
            if (((Activity) mContxt).isDestroyed()) {
                return;
            }
        }
        try {
            Glide.with(mContxt)
                    .load(imageUri)
                    .into(myImageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Glible加载图片接口回调
     */
//    public interface GlideListener {
//        void onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource);
//
//        void onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource);
//    }

    /**
     * 加载资源文件的gif动图
     *
     * @param mContxt     传调用页面的上下文 最好传Activity、Fragment、FragmentActivity 因为加载图片跟这三个生命周期绑定了 会提高效率 节省资源
     * @param gifResource drawable中的gif资源
     * @param myImageView 图片控件
     */
//    public void showGif(Context mContxt, int gifResource, ImageView myImageView) {
//        if (null == mContxt || null == myImageView) {
//            return;
//        }
//
//        if (mContxt instanceof Activity) {
//            if (((Activity) mContxt).isDestroyed()) {
//                return;
//            }
//        }
//
//        Glide.with(mContxt)
//                .asGif()
//                .load(gifResource)
//                .into(myImageView);
//    }

    /**
     * <从相册或者拍照获取图片>
     */
//    public void choiceImage(final Activity mContext, final String imagePath) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//        builder.setTitle(mContext.getResources().getIdentifier("choiceType", "string", mContext.getPackageName()))
//                .setIcon(android.R.drawable.ic_dialog_info)
//                .setItems(mContext.getResources().getIdentifier("choiceImageStyle", "array", mContext.getPackageName()),
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                switch (which) {
//                                    case 0:
//                                        // 拍照
//                                        takePicture(mContext, imagePath);
//                                        break;
//
//                                    case 1:
//                                        // 去相册
//                                        selectPhoto(mContext);
//                                        break;
//
//                                    case 2:
//                                        break;
//
//                                    default:
//                                        break;
//                                }
//                            }
//                        })
//                .create()
//                .show();
//    }

    /**
     * 通过手机照相获取图片
     *
     * @param activity
     * @return 照相后图片的路径
     */
//    public void takePicture(Activity activity, String imagePath) {
//        try {
//            // 拍照第一步：首先判断SDCard是否存在
//            String ROOT_PATH = TelephoneUtil.getInstance().getSDPath();
//
//            if (null == ROOT_PATH) {
//                // 拍照第二步：如果sdcard不存在，就判断内存卡的容量（如果容量大于50MB就拍照）
//                long phoneMemory = TelephoneUtil.getInstance().getROMAvailableSize();
//                if (phoneMemory > 52428800) {
//                    // 50MB
//                    ROOT_PATH = Environment.getDataDirectory() + File.separator;
//                } else {
//                    Toast.makeText(activity,
//                            activity.getResources().getIdentifier("memory_notfull", "string", activity.getPackageName()),
//                            Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//
//            // 创建图片Image文件夹目录
//            File dir = FileUtil.getInstance().createDirectory(imagePath);
//            cameraFile =
//                    FileUtil.getInstance().createNewFile(dir.getAbsolutePath() + File.separator + UUID.randomUUID()
//                            + ".png");
//            /* 调用系统自带的照相意图 */
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            /* 将拍照的图片保存到sd卡中或者内存中 */
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
//            activity.startActivityForResult(intent, INTENT_REQUEST_CODE_CAMERA);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 通过手机相册获取图片
     *
     * @param activity
     */
    public void selectPhoto(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        activity.startActivityForResult(intent, INTENT_REQUEST_CODE_ALBUM);
    }

    /**
     * <Bitmap 转化为 byte[]>
     *
     * @param bm
     * @return
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * <byte[] 转化为 Bitmap>
     *
     * @param b
     * @return
     */
    public Bitmap Bytes2Bimap(byte[] b) {
        if (null != b && b.length != 0) {
            try {
                return BitmapFactory.decodeByteArray(b, 0, b.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        return null;
    }

    //    @Override
    //    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    //    {
    //        if (resultCode == RESULT_OK)
    //        {
    //            switch (requestCode)
    //            {
    //                case ImageUtil.INTENT_REQUEST_CODE_CAMERA:
    //                    /**
    //                     * 拍照回调
    //                     */
    //                    try
    //                    {
    //                        Bitmap headBitmap = ImageUtil.getInstance().readSDorResBitmap(mContext,
    //                            ImageUtil.getInstance().cameraFile.getAbsolutePath(),
    //                            0);
    //                        if (null != headBitmap && null != InDoorFragment.selfmainActivity)
    //                        {
    //                            //調用InDoorFragment裡邊的設置圖片方法
    //                            InDoorFragment.selfmainActivity.setHeadBitmap(headBitmap);
    //                            InDoorFragment.selfmainActivity.setImageView(headBitmap);
    //                        }
    //                        else
    //                        {
    //                            showToast(mContext, R.string.getHeadFailure);
    //                        }
    //                    }
    //                    catch (Exception e)
    //                    {
    //                        e.printStackTrace();
    //                    }
    //                    break;
    //
    //                case ImageUtil.INTENT_REQUEST_CODE_ALBUM:
    //                    /**
    //                     * 从相册选取图片作为头像
    //                     */
    //                    finishActivity(2);
    //                    if (null != data)
    //                    {
    //                        try
    //                        {
    //                            String path = "";
    //                            Uri uri = data.getData();
    //
    //                            String[] proj = {MediaStore.Images.Media.DATA};
    //
    //                            //好像是android多媒体数据库的封装接口，具体的看Android文档
    //                            Cursor cursor = managedQuery(uri, proj, null, null, null);
    //                            if (null != cursor)
    //                            {
    //                                //按我个人理解 这个是获得用户选择的图片的索引值
    //                                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    //                                //将光标移至开头 ，这个很重要，不小心很容易引起越界
    //                                cursor.moveToFirst();
    //                                //最后根据索引值获取图片路径
    //                                path = cursor.getString(column_index);
    //                            }
    //                            else
    //                            {
    //                                path = uri.getPath();
    //                            }
    //
    //                            Bitmap headBitmap = ImageUtil.getInstance().readSDorResBitmap(mContext, path, 0);
    //
    //                            if (null != headBitmap && null != InDoorFragment.selfmainActivity)
    //                            {
    //                                InDoorFragment.selfmainActivity.setHeadBitmap(headBitmap);
    //                                InDoorFragment.selfmainActivity.setImageView(headBitmap);
    //                            }
    //                            else
    //                            {
    //                                showToast(mContext, R.string.getHeadFailure);
    //                            }
    //                        }
    //                        catch (Exception e)
    //                        {
    //                            e.printStackTrace();
    //                        }
    //                    }
    //                    break;
    //
    //                default:
    //                    break;
    //            }
    //        }
    //        super.onActivityResult(requestCode, resultCode, data);
    //    }

    /**
     * 拍照或者从相册选取图片作为头像 有部分手机会旋转，此方法就是为了矫正图片的
     *
     * @param path   图片的路径
     * @param bitmap 图片的Bitmap类型
     * @return 返回矫正后的图片 Bitmap
     */
    public Bitmap correctPicture(String path, Bitmap bitmap) {
        try {
            // 首先得到图片的旋转角度
            int degree = getBitmapDegree(path);
            if (0 != degree) {
                // 其次根据图片的旋转角度，旋转对应角度
                return rotateBitmapByDegree(bitmap, degree);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    private int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation =
                    exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
                default:
                    degree = 0;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            degree = 0;
        }
        return degree;
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    private Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree - 90);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (null == returnBm) {
            returnBm = bm;
        }
        if (bm != returnBm) {
            bm.recycle();
        }
        return returnBm;
    }

    /**
     * 获取图片的长度和宽度
     *
     * @param bitmap 图片bitmap对象
     * @return
     */
    public Bundle getBitmapWidthAndHeight(Bitmap bitmap) {
        Bundle bundle = null;
        if (bitmap != null) {
            bundle = new Bundle();
            bundle.putInt("width", bitmap.getWidth());
            bundle.putInt("height", bitmap.getHeight());
            return bundle;
        }
        return null;
    }

    /**
     * <截屏并保存图片>
     */
//    public File screenShotsImage(Activity context, String path, int screenWidth, int screenHeight)
//            throws Exception {
//        //1.获取屏幕Bitmap
//        View decorview = context.getWindow().getDecorView();
//        decorview.setDrawingCacheEnabled(true);
//        decorview.buildDrawingCache();
//        Bitmap Bmp = decorview.getDrawingCache();
//
//        //2.去掉标题栏 构建Bitmap
//        Rect frame = new Rect();
//        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;
//        Bmp =
//                Bitmap.createBitmap(Bmp,
//                        0,
//                        statusBarHeight,
//                        screenWidth,
//                        screenHeight - statusBarHeight);
//
//        //3.保存Bitmap
//        File rootFile = FileUtil.getInstance().createDirectory(path);
//        File pathFile =
//                FileUtil.getInstance().createNewFile(rootFile.getAbsolutePath() + File.separator
//                        + StringUtil.getInstance().getUUID() + ".png");
//        FileOutputStream fos = new FileOutputStream(pathFile);
//        if (null != Bmp) {
//            Bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
//        }
//        fos.flush();
//        fos.close();
//
//        //4.销毁缓存信息
//        decorview.destroyDrawingCache();
//        if (null != Bmp && !Bmp.isRecycled()) {
//            Bmp.recycle();
//        }
//        return pathFile;
//    }

    public void pauseRequests(Context mContxt) {
        try {
            Glide.with(mContxt).pauseRequests();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeRequests(Context mContxt) {
        try {
            Glide.with(mContxt).resumeRequests();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}