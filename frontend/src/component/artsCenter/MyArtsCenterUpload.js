import MyButton from "../button/MyButton";
import { useRef, useState } from "react";
import axios from "axios";
import PostAxios from "../../utils/axios/PostAxios";
import { useNavigate, useParams } from "react-router-dom";


const MyArtsCenterUpload = () => {

    const imageInput = useRef();
    const [prevImage, setPrevImage] = useState("");
    const [image, setImage] = useState("");
    const navigate = useNavigate();

    const { contestId } = useParams();

    const [inputs, setInputs] = useState({
        title : '',
        description : 1,
        content : '',
      });
  
      const {title, description} = inputs
  
      const onChange = (e) => {
          const {name, value} = e.target;
          setInputs({
              ...inputs,
              [name] : value
          });
      };



    const saveFileImage = (e) => {
        setPrevImage(URL.createObjectURL(e.target.files[0]));
        setImage(e.target.files[0]);
    }

    const onClickImageUpload = () => {
        imageInput.current.click();
    }

    const onDrop = async() => {
        let formData = new FormData();
        formData.append("api_key", "516516722966632");
        formData.append("upload_preset", "vspuoidk");
        formData.append("timestamp", (Date.now() / 1000) | 0);
        formData.append(`file`, image);

        console.log(formData);

        const config = {
            header: { "Content-Type": "multipart/form-data" }
        }

        await axios.post('https://api.cloudinary.com/v1_1/dlcpn7mul/image/upload', formData, config)
        .then(res=>{

            const requestBody = {
                memberId: sessionStorage.getItem("userId"),
                contestId: contestId,
                title: title,
                contestPaintingUrl: res.data.url,
                description: description,
            };

            PostAxios('/arts-center/painting', requestBody, (res)=>{
                navigate('/arts-center')
            });
        })
    }

    return (
        <>
    <div className="arts_center_wrapper">
        <img className='arts_center_logo' src={process.env.PUBLIC_URL + `../../img/arts_center_logo.png`} />
    <br/>
    <br/>
    </div>
    <br/>
    <div className="contest_wrapper">

    <MyButton text={'이미지 업로드'} onClick={onClickImageUpload}/>
    <input type='file' name="imgUpload" style={{display:"none"}} ref={imageInput} onChange={saveFileImage} accept="image/*"/>
    <MyButton type={'positive'} text={'제출'} onClick={onDrop}/>
    <br/>
    <br/>
    <input className="contest_title" type='text' name="title" onChange={onChange}
     placeholder="공모전 제목을 입력해주세요." maxLength='20'/>
    <br/>
    <br/>
    <div className="contest_upload">
    {prevImage && (
                    <img alt="painting"
                    className="contest_upload_img"
                    src={prevImage}
                    style= {{ margin:"auto"}} />
                )}
    <br/>
    <div className="input_box">
        <textarea name="description" onChange={onChange}
        className="input_comment" placeholder="공모전 설명을 입력해주세요. 공모전 설명은 생략이 가능하나 공정성을 위해 수정이 불가하오니 신중히 작성해주세요."/>
    </div>
    </div>
    <div className="contest_post_box">
    </div>
    </div>
    </>
    );
}

export default MyArtsCenterUpload;