import MyButton from "../../button/MyButton";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import PostAxios from "../../../utils/axios/PostAxios";


const MyDrops = ({options}) => {

    const completionWord = 'Please make your drops or dreams! ';
    const [message, setMessage] = useState('');
    const [count, setCount] = useState(0);

    const navigate = useNavigate();
    
    const [inputs, setInputs] = useState({
      typeId : 1,
      genreId : 0,
      name : '',
    });

    const {typeId, genreId, name} = inputs

    const onChange = (e) => {
        const {name, value} = e.target;
        setInputs({
            ...inputs,
            [name] : value
        });
    };

    useEffect(() => {
        const typingInterval = setInterval(() => {
            setMessage((prevTitleValue) => {
            let result = prevTitleValue ? prevTitleValue + completionWord[count] : completionWord[0];
            setCount(count + 1);
    
            if (count >= completionWord.length) {
              setCount(0);
              setMessage('');
            }
            return result;
          });
        }, 300);
    
        return () => {
          clearInterval(typingInterval);
        };
      });

      const DropsRequestBody = {
        userId:sessionStorage.getItem("userId"), 
        typeId: typeId,
        name: name,
        genreId : genreId,
    };

      const onClick = () => {
        PostAxios('/drops',DropsRequestBody,(res)=>{navigate('/home/upload')})
        
      }

    return (
            <>
            <div className="upload_drops">
            <span className="animation_message">{message}</span> 
            <br/>
            <br/>
            <br/>
            <br/>
            <br/>
            비밀 여부 : <select className="select_box" name="typeId" onChange={onChange}>
                <option key={1} value={1}>일반 드랍</option>
                <option key={2} value={2}>비밀 드랍</option>
            </select>
            장르 : <select className="select_box" name="genreId" onChange={onChange}>
                {options.map(option=>(
                    <option key={option.id} value={option.id}>{option.name}</option>
                ))}
            </select>
            <br/>
            <br/>
            <input className="drops_text" type='text' name="name" onChange={onChange} placeholder=" 드랍 제목을 입력해주세요."/>
            <br/>
            <div>
            <br/>
            <MyButton text={'드랍 생성'} onClick={onClick}/>
            <MyButton type={'negative'} text={'초기화'}/>
            </div>
            </div>
        </>
    );
}

export default MyDrops;